/**
 * Created by IntelliJ IDEA.
 * Project: Messenger-Connector
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-09-25
 * Time: 오후 9:39
 */

//// [Options]
/**
 * Data
 * @type {{ADMIN: string, JARAM_SLACK: string, JARAM_ROOM: string, MAEHWA: string, TEST: string}}
 */
var Rooms = {
    ADMIN: 'admin', // host account
    TEST: 'test', // test room
    JARAM_ROOM: 'jaram',
};

/**
 * Settings
 * @type {{PROFILE: number, CONNECTOR_SERVER: string, TEST_URL: string, SEND_URL: string, RECEIVE_URL: string, SLACK_SEND_URL: string, LISTENING_INTERVAL: number, IS_LISTENING: boolean, IS_SENDING: boolean}}
 */
var Settings = {
    PROFILE: 1, // 1: prod | 2: test
    CONNECTOR_SERVER: '',
    TEST_URL: 'https://www.google.com',
    SEND_URL: 'http://' + this.CONNECTOR_SERVER + '/kakaotalk/send',
    RECEIVE_URL: 'http://' + this.CONNECTOR_SERVER + '/kakaotalk/receive?',
    SLACK_SEND_URL: 'http://' + this.CONNECTOR_SERVER + '/slack/send', // temp
    LISTENING_INTERVAL: 1000, // The time interval of http call
    IS_LISTENING: false,
    IS_SENDING: false
};

/**
 * Mobile
 * @type {{GALAXY_S4: string}}
 */
var Mobile = {
    GALAXY_S4: 'ks01lteskt'
};


/**
 * Connection Function
 * @param room_in
 * @param room_out
 * @constructor
 */
// Connecting between two rooms
function Connect(room_in, room_out) {
    if (checkRoom(room_in)) this.room_in = room_in;
    if (checkRoom(room_out)) this.room_out = room_out;

    // If there have no correct rooms, send the messagehub to ADMIN(host account)
    if (this.room_in === undefined || this.room_out === undefined) {
        var error_room = '';
        if (this.room_in === undefined && this.room_out === undefined) {
            error_room = room_in + '" and "' + room_out;
        } else if (this.room_in === undefined) {
            error_room = room_in;
        } else {
            error_room = room_out;
        }
        Api.replyRoom(Rooms.ADMIN, 'There have no correct rooms of "' + error_room + '"');
    }
}

// Return room_in and room_out
Connect.prototype.getRooms = function() {
    return [this.room_in, this.room_out];
};

// Override toString
Connect.prototype.toString = function() {
    Api.replyRoom(this.room_out,
        'Input Room : ' + this.room_in +
        '\nOutput Room : ' + this.room_out
    );
};

////
// Message msg function inherited Connect
function Message(room_in, room_out, msg, sender) {
    Connect.call(this, room_in, room_out);
    this.msg = msg;
    this.sender = sender;
}
Message.prototype = Object.create(Connect.prototype); // inherit Connect
Message.prototype.constructor = Connect; // correcting the constructor pointer

//// Api method
// Message msg to 'room_out'
Message.prototype.replyRoom = function() {
    Api.replyRoom(this.room_out, this.msg);
};

//// Util method
// Waiting 'ms' millisecond and resume the source
Message.prototype.delay = function(ms) {
    Utils.delay(ms);
};
// Getting web text
Message.prototype.getWebText = function(url) {
    return Utils.getWebText(url);
};
// Parsing the particular DOM, and bring to HTML
Message.prototype.parseToHtml = function(url) {
    return Utils.parseToHtml(url);
};
// Parsing the particular DOM, and brint to TXT
Message.prototype.parseToText = function(url) {
    return Utils.parseToText(url);
};

////
// Override toString
Message.prototype.toString = function() {
    Api.replyRoom(this.room_out,
        'Input Room : ' + this.room_in +
        '\nOutput Room : ' + this.room_out +
        '\nSender : ' + this.sender +
        '\nMessage : ' + this.msg
    );
};

/**
 * Functions
 */
// Check the room in Rooms
function checkRoom(room_in) {
    var check = false;
    for (var room in Rooms) {
        if (Rooms[room] === room_in) check = true;
    }
    return check;
}

// Check the input room in Rooms
function checkInputRoom(room_in) {
    if (checkRoom(room_in) === false)
        Api.replyRoom(Rooms.ADMIN, 'There have no input room :: "' + room_in + '"');
}

// Check Settings.CONNECTOR_SERVER
function checkConnectServer(room_from) {
    if (Settings.CONNECTOR_SERVER === '') {
        Api.replyRoom(room_from, 'No connected server exist.');
        return false;
    }
    return true;
}

// Check if admin room
function checkAdminRoom(room_from) {
    if (room_from !== Rooms.ADMIN) {
        Api.replyRoom(room_from, 'This room is NOT admin room.');
        return false;
    }
    return true;
}

function listenMessage(room_from, room_to) {
    while(Settings.IS_LISTENING) {
        try {
            var request_body = Utils.getWebText(Settings.SEND_URL);
            var trimmed_text = (request_body.replace(/(<([^>]+)>)/ig, '')).trim();
            var json_data = JSON.parse(trimmed_text);
            var message_list = json_data.message_list;

            for (var i in message_list) {
                var sender = message_list[i].sender;
                var context = message_list[i].context;
                var messenger_from = message_list[i].messenger_from;

                var msg_out = '[' + sender + ' | ' + messenger_from + ']\n' + context;
                var received_message = new Message(room_from, room_to, msg_out, sender);

                received_message.replyRoom();
            }
            java.lang.Thread.sleep(Settings.LISTENING_INTERVAL);
        } catch (e) {
            Settings.IS_LISTENING = false;
            throw new ServerException('The server is disconnected. (Couldn\'t listen)', room_from);
        }
    }
}

function commandController(msg, room_from, room_to) {
    var split_msg = msg.split("=");
    var command = split_msg[0];
    var value = split_msg[1];

    // Setting environments
    if (command === '$set_profile') {
        if (checkAdminRoom(room_from)) {
            Settings.PROFILE = value;
            Api.replyRoom(room_from, 'Success setting profile.');
        }
    } else if (command === '$set_server') {
        if (checkAdminRoom(room_from)) {
            Settings.CONNECTOR_SERVER = value;
            Settings.SEND_URL = 'http://' + value + '/kakaotalk/send';
            Settings.RECEIVE_URL = 'http://' + value + '/kakaotalk/receive?';
            Settings.SLACK_SEND_URL = 'http://' + value + '/slack/send';

            Api.replyRoom(room_from, 'Success setting connector server address.');
        }
    }

    // Getting environments
    if (msg === '$get_settings') {
        var str = '';
        for (var setting in Settings) {
            str += setting + ' : ' + Settings[setting] + '\n';
        }
        Api.replyRoom(Rooms.ADMIN, str);
    } else if (msg === '$get_rooms') {
        var str = '';
        for (var room in Rooms) {
            str += room + ' : ' + Rooms[room] + '\n';
        }
        Api.replyRoom(Rooms.ADMIN, str);
    }

    // Connecting Server
    if (msg === '$send_start') {
        if (checkConnectServer(room_from)) {
            Settings.IS_SENDING = true;
            Api.replyRoom(room_from, 'Sender is running ...');
        }
    } else if (msg === '$send_stop') {
        Settings.IS_SENDING = false;
        Api.replyRoom(room_from, 'Sender is stopped ...');
    } else if (msg === '$listen_start') {
        if (checkConnectServer(room_from)) {
            Settings.IS_LISTENING = true;
            Api.replyRoom(room_from, 'Listener is running ...');
            listenMessage(room_from, room_to);
        }
    } else if (msg === '$listen_stop') {
        Settings.IS_LISTENING = false;
        Api.replyRoom(room_from, 'Listener is stopped ...');
    } else {
        // message.replyRoom();
    }
}

/**
 * Exception
 */
function ServerException(message, room_from) {
    this.message = message;
    this.room_from = room_from;
    this.name = 'ServerException';
    Api.replyRoom(this.room_from, this.name + ' : ' + this.message);
}

/**
 * Operating Bot
 */
function comm(room, msg, sender, isGroupChat, replier, imageDB) {
    // [For Jaram academy]
    if (room === Rooms.JARAM_ROOM) {
        var room_from = room;
        var room_to = room;
        var message = new Message(room_from, room_to, msg, sender);
        commandController(msg, room_from, room_to);
        if (Settings.IS_SENDING) {
            try {
                var receive_url = (Settings.RECEIVE_URL
                    + 'channel_from=' + room_from
                    + '&sender=' + sender
                    + '&context=' + msg
                );
                message.getWebText(receive_url);
                message.getWebText(Settings.SLACK_SEND_URL);
            } catch (e) {
                Settings.IS_SENDING = false;
                throw new ServerException('The server is disconnected. (Couldn\'t send)', room_from);
            }
        }
    } else if (room === Rooms.ADMIN) {
        testComm(room, msg, sender, isGroupChat, replier, imageDB);
    } else if (room === Rooms.TEST) {
        testComm(room, msg, sender, isGroupChat, replier, imageDB);
    }
}

/**
 * Test Operating Bot
 */
function testComm(room, msg, sender, isGroupChat, replier, imageDB) {
    var room_from = room;
    var room_to = room;
    var message = new Message(room_from, room_to, msg, sender);
    // message.toString();
    commandController(msg, room_from, room_to);
    if (msg === 'get_test') {
        message.msg = message.getWebText(Settings.TEST_URL);
        message.replyRoom();
    }
    if (Settings.IS_SENDING) {
        try {
            var receive_url = (Settings.RECEIVE_URL
                + 'channel_from=' + room_from
                + '&sender=' + sender
                + '&context=' + msg
            );
            message.getWebText(receive_url);
            message.getWebText(Settings.SLACK_SEND_URL);
        } catch (e) {
            Settings.IS_SENDING = false;
            throw new ServerException('The server is disconnected. (Couldn\'t send)', room_from);
        }
    }
}

/**
 * Main
 */
function response(room, msg, sender, isGroupChat, replier, imageDB) {
    checkInputRoom(room);
    msg = msg.trim();
    // connecting 'test' Room
    if (Settings.PROFILE === 1) {
        comm(room, msg, sender, isGroupChat, replier, imageDB);
    } else if (Settings.PROFILE === 2) {
        testComm(room, msg, sender, isGroupChat, replier, imageDB);
    }
}

function onCreate(savedInstanceState, activity) {}
function onResume(activity) {}
function onPause(activity) {}
function onStop(activity) {}