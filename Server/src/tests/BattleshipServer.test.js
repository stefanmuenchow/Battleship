var BattleshipServer = require('../BattleshipServer');
var events = require('events');
var should = require('should');

// create the socket mock object
function SocketMock() {};
SocketMock.prototype.__proto__ = events.EventEmitter.prototype;

// describe the expected behaviour
describe('BattleshipServer', function () {

	var server = new BattleshipServer({
		port: 5000
	});

	describe('Connection', function () {
		it('must fire a playerJoined event, when a new player joind the server', function (done) {
			var theSocketMock = new SocketMock();
			server.onClientConnected(theSocketMock);
			server.once('playerJoined', function () {
				server.should.have.property('clients').with.lengthOf(1);
				server.should.have.property('rooms').with.lengthOf(1);
				done();
			});
			// simulate a tcp message
			theSocketMock.emit('data', new Buffer('JOIN GAME-ROOM-1 TEST-PLAYER', 'utf-8'));
		});

		it('must fire a playerJoinFailed event, if a player with an already joined player name want to join the server', function (done) {
			var playerOneSocket = new SocketMock();
			var playerTwoSocket = new SocketMock();

			server.onClientConnected(playerOneSocket);
			server.onClientConnected(playerTwoSocket);

			server.once('playerJoinFailed', function () {
				// the first player should be joined, so the clients array must have a length of 1 and not of 2
				server.should.have.property('clients').with.lengthOf(1);
				server.should.have.property('rooms').with.lengthOf(1);

				done();
			});

			playerOneSocket.emit('data', new Buffer('JOIN GAME-ROOM-1 TEST-PLAYER', 'utf-8'));
			playerTwoSocket.emit('data', new Buffer('JOIN GAME-ROOM-1 TEST-PLAYER', 'utf-8'));
		});
		
	});

	it('must fire a playerLeft event, when the player sends the bye message', function(done) {
		var playerOneSocket = new SocketMock();
		var playerTwoSocket = new SocketMock();

		server.onClientConnected(playerOneSocket);
		server.onClientConnected(playerTwoSocket);

		server.once('playerLeft', function () {
			// the first player should be joined, so the clients array must have a length of 1 and not of 2
			server.should.have.property('clients').with.lengthOf(1);
			server.should.have.property('rooms').with.lengthOf(1);

			done();
		});

		playerOneSocket.emit('data', new Buffer('JOIN GAME-ROOM-1 TEST-PLAYER1', 'utf-8'));
		playerTwoSocket.emit('data', new Buffer('JOIN GAME-ROOM-1 TEST-PLAYER2', 'utf-8'));

		server.should.have.property('clients').with.lengthOf(2);
		server.should.have.property('rooms').with.lengthOf(1);

		server.clients[0].emit('bye', server.clients[0]);
	});
});