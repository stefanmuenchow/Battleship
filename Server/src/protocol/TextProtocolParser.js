var events = require('events');
var Flux = require('node-flux');

/**
 * parser for the Battleship text (utf-8) protocol
 */
function TextProtocolParser (args) {
	this.message = '';
	this.client = args.client;

	// JOIN <room name> <player name>
	this.JOIN_MESSAGE = new Flux().find('JOIN ').word().then(' ').word();

	// LINEUP <player name> <lineup e.g. a4;a5;...>
	this.LINEUP_MESSAGE = new Flux().find('LINEUP ').word().then(' ').raw('([a-zA-Z0-9;]+)');

	// READY <player name>
	this.READY_MESSAGE = new Flux().find('READY ').word();

	// SHOT <player name> <shot position e.g. a7>
	this.SHOT_MESSAGE = new Flux().find('SHOT ').word().then(' ').raw('([a-zA-Z0-9]+)');

	// BYE <player name>
	this.BYE_MESSAGE = new Flux().find('BYE ').word();
} 

// inherhit from event base class
TextProtocolParser.prototype.__proto__ = events.EventEmitter.prototype;

/**
 * parses an incoming buffer
 *
 * @param buffer the buffer to parse
 */
TextProtocolParser.prototype.parse = function (buffer) {
	this.message += buffer.toString('utf-8');

	console.log('Try to parse message: ', this.message);

	if (this.JOIN_MESSAGE.match(this.message)) {
		// execute the reqular expression to get the matches
		var joinMatches = new RegExp(this.JOIN_MESSAGE.compile(), this.JOIN_MESSAGE.modifiers.join()).exec(this.message);
		var roomName = joinMatches [2];
		var playerName = joinMatches[4]; 

		console.log ('join message received. Game room: ' + roomName + ', Player name: ' + playerName);
		this.client.onJoin(roomName, playerName);

	} else if (this.LINEUP_MESSAGE.match(this.message)) {
		// execute the reqular expression to get the matches
		var lineupMatches = new RegExp(this.LINEUP_MESSAGE.compile(), this.LINEUP_MESSAGE.modifiers.join()).exec(this.message);
		var playerName = lineupMatches[2]; 
		var lineup = lineupMatches [4];

		console.log('lineup message received. Player name: ' + playerName + ', Lineup: ', lineup.split(';'));
		this.client.onLineup(lineup.split(';'));

	} else if (this.READY_MESSAGE.match(this.message)) {
		// execute the reqular expression to get the matches
		var readyMatches = new RegExp(this.READY_MESSAGE.compile(), this.READY_MESSAGE.modifiers.join()).exec(this.message);
		var playerName = readyMatches[2]; 
		
		console.log('ready message received. Player name: ' + playerName);
		this.client.onReady();

	} else if (this.SHOT_MESSAGE.match(this.message)) {
		// execute the reqular expression to get the matches
		var shotMatches = new RegExp(this.SHOT_MESSAGE.compile(), this.SHOT_MESSAGE.modifiers.join()).exec(this.message);
		var playerName = shotMatches[2];
		var shotPos = shotMatches[4]; 

		console.log('shot message received. Player name: ' + playerName + ", shot position: " + shotPos);
		this.client.onShot(shotPos);

	} else if (this.BYE_MESSAGE.match(this.message)) {
		// execute the reqular expression to get the matches
		var byeMatches = new RegExp(this.BYE_MESSAGE.compile(), this.BYE_MESSAGE.modifiers.join()).exec(this.message);
		var playerName = byeMatches[2];

		console.log('bye message received. Player name: ' + playerName);
		this.client.onBye();

	} else {
		return;
	}

	// reset message
	this.message = '';
}

module.exports = TextProtocolParser;