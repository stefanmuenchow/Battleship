// load the TCP Library
var net = require('net'),
    events = require('events'),
    _ = require('lodash'),
    BattleshipClient = require('./BattleshipClient'),
    GameRoom = require('./common/GameRoom'),
    ProtocolParser = require('./protocol/TextProtocolParser');

/**
 * requires an object with the port to listen on 
 * @param: {port: <PORT>}
 *
 * @events:
 *          playerJoined - Fired when a player joind the server and a new gmae romm
 *          playerJoinFailed - Fired when the user sends invalid player name or want to join a room that has already enogh players
 */
function BattleshipServer (args) {

	// array with all connected clients
	this.clients = [];
	
	// map with all game rooms
	this.rooms = [];

	// start the TCP server
	net.createServer(this.onClientConnected).listen(args.port);
};

BattleshipServer.prototype.__proto__ = events.EventEmitter.prototype;

/**
 * handles new clients
 */
BattleshipServer.prototype.onClientConnected = function (socket) {
	var client = new BattleshipClient(socket, new ProtocolParser());
	client.on('join', this.onJoin);
	client.once('bye', this.onBye);
	this.clients.push(client);
};

/**
 * handles the request from a client to join a room
 */ 
BattleshipServer.prototype.onJoin = function (client, args) {
	if (this.isPlayerNameInUse(args.playerName)) {
		client.joinFailure('Player name <' + args.playerName + '> is already in use.');

	} else if (this.isGameRoomComplete(args.roomName)) {
		client.joinFailure('The room <' + args.roomName + '> you want to join is already complete.');

	} else {
		this.assignClientToRoom(client, args.roomName);
	}
};

/**
 * handles the request from a client to left the game
 */ 
BattleshipServer.prototype.onBye = function (client) {
	_.remove(this.clients, function (c) {
		return c.name === client.name;
	});
};

/**
 * handles the request from a client to join a room
 */ 
BattleshipServer.prototype.isGameRoomComplete = function (roomName) {
	if (_.has(this.rooms, roomName)) {
		return this.rooms[roomName].isComplete();
	}

	return false;
};

/**
 * handles the request from a client to join a room
 */ 
BattleshipServer.prototype.isPlayerNameInUse = function (playerName) {
	var c = _.find(this.clients, {'name': playerName});
	return c !== undefined;
};

module.exports = BattleshipServer;