var events = require('events');
var parser = require('./protocol/TextProtocolParser');

/**
 *
 *
 * @events: 
 *          closed - if the tcp socket was closed
 *          bye    - if the client want to left the game
 *          join   - if the client want to join a room
 *          lineup - if the user sends its line up
 *          ready  - if the user is ready for the next shot
 *          shot   - executes a shot
 */
function BattleshipClient (socket) {
	this.socket = socket;
	events.EventEmitter.call(this);

	socket.on('data', this.onDataReceived);
	socket.on('end', this.onSocketClosed);
};

BattleshipClient.prototype.__proto__ = events.EventEmitter.prototype;

BattleshipClient.prototype.onDataReceived = function (data) {
	console.log("Message received: ", data.toString('utf-8'));
	parser.parse(data.toString('utf-8'), this);
};

BattleshipClient.prototype.onSocketClosed = function () {
	this.emit('closed');
};

module.exports = BattleshipClient;