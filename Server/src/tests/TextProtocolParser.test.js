var Parser = require('../protocol/TextProtocolParser');
var should = require('should');


describe('Text protocol parser', function () {

	describe('JOIN message', function () {
		it('should contain a room and a player name', function (done) {

			var messageBuffer = new Buffer('JOIN GameRoom PlayerName\n', 'utf-8');
			var parser = new Parser({
				client: {
					onJoin: function (roomName, playerName) {
						(roomName   != undefined).should.be.true;
						(playerName != undefined).should.be.true;

						(roomName   === 'GameRoom').should.be.true;
						(playerName === 'PlayerName').should.be.true;

						done();
					}
				}
			});

			parser.parse(messageBuffer);

		});

		it('should receive a message in multiple buffers', function (done) {
			var bufferOne = new Buffer('JOIN GameRoom', 'utf-8');
			var bufferTwo = new Buffer(' PlayerName', 'utf-8');

			var parser = new Parser({
				client: {
					onJoin: function (roomName, playerName) {
						(roomName   != undefined).should.be.true;
						(playerName != undefined).should.be.true;

						(roomName   === 'GameRoom').should.be.true;
						(playerName === 'PlayerName').should.be.true;

						done();
					}
				}
			});

			parser.parse(bufferOne);
			parser.parse(bufferTwo)
		});

		it('should ignore an invalid message start', function (done) {
			var messageBuffer = new Buffer('INvalid message start#wrr JOIN GameRoom PlayerName\n', 'utf-8');
			var parser = new Parser({
				client: {
					onJoin: function (roomName, playerName) {
						(roomName   != undefined).should.be.true;
						(playerName != undefined).should.be.true;

						(roomName   === 'GameRoom').should.be.true;
						(playerName === 'PlayerName').should.be.true;

						done();
					}
				}
			});

			parser.parse(messageBuffer);
		});
	});

	describe('LINEUP message', function () {
		it('should contain a player name and a lineup array', function (done) {

			// use 7 positions for our test case
			var messageBuffer = new Buffer('LINEUP PlayerName a7;a8;19;c3;d3;e3;f3', 'utf-8');
			var parser = new Parser({
				client: {
					onLineup: function (lineup) {
						(lineup     != undefined).should.be.true;
						(lineup.length === 7).should.be.true;

						done();
					}
				}
			});

			parser.parse(messageBuffer);

		});

		it('should receive a message in multiple buffers', function (done) {

			// use 7 positions for our test case
			var bufferOne = new Buffer('LINEUP PlayerName ', 'utf-8');
			var bufferTwo = new Buffer('a7;a8;19;c3;d3;e3;f3', 'utf-8');

			var parser = new Parser({
				client: {
					onLineup: function (lineup) {
						(lineup     != undefined).should.be.true;
						(lineup.length === 7).should.be.true;

						done();
					}
				}
			});

			parser.parse(bufferOne);
			parser.parse(bufferTwo);

		});

		it('should ignore an invalid message start', function (done) {

			// use 7 positions for our test case
			var messageBuffer = new Buffer('asdasdasd#+23213LINEUP PlayerName a7;a8;19;c3;d3;e3;f3', 'utf-8');
			var parser = new Parser({
				client: {
					onLineup: function (lineup) {
						(lineup     != undefined).should.be.true;
						(lineup.length === 7).should.be.true;

						done();
					}
				}
			});

			parser.parse(messageBuffer);

		});
	});

	describe('READY message', function () {
		it('should contain a player name', function (done) {

			// use 7 positions for our test case
			var messageBuffer = new Buffer('READY PlayerName', 'utf-8');
			var parser = new Parser({
				client: {
					onReady: function () {
						done();
					}
				}
			});

			parser.parse(messageBuffer);

		});

		it('should receive a message in multiple buffers', function (done) {

			var bufferOne = new Buffer('READY ', 'utf-8');
			var bufferTwo = new Buffer('PlayerName', 'utf-8');

			var parser = new Parser({
				client: {
					onReady: function () {
						done();
					}
				}
			});

			parser.parse(bufferOne);
			parser.parse(bufferTwo);

		});

		it('should ignore an invalid message start', function (done) {

			// use 7 positions for our test case
			var messageBuffer = new Buffer('asdasdasd#+23213READY PlayerName', 'utf-8');
			var parser = new Parser({
				client: {
					onReady: function () {
						done();
					}
				}
			});

			parser.parse(messageBuffer);

		});
	});

	describe('SHOT message', function() {
		it('should contain a player name and a shot position', function (done) {

			// use 7 positions for our test case
			var messageBuffer = new Buffer('SHOT PlayerName a7', 'utf-8');
			var parser = new Parser({
				client: {
					onShot: function (shotPosition) {
						(shotPosition !== undefined).should.be.true;
						(shotPosition === 'a7').should.be.true;

						done();
					}
				}
			});

			parser.parse(messageBuffer);

		});

		it('should receive a message in multiple buffers', function (done) {

			// use 7 positions for our test case
			var bufferOne = new Buffer('SHOT PlayerName ', 'utf-8');
			var bufferTwo = new Buffer('a7', 'utf-8');

			var parser = new Parser({
				client: {
					onShot: function (shotPosition) {
						(shotPosition !== undefined).should.be.true;
						(shotPosition === 'a7').should.be.true;

						done();
					}
				}
			});

			parser.parse(bufferOne);
			parser.parse(bufferTwo);

		});

		it('should ignore an invalid message start', function (done) {

			// use 7 positions for our test case
			var messageBuffer = new Buffer('asdasdasd#+23213SHOT PlayerName a7', 'utf-8');
			var parser = new Parser({
				client: {
					onShot: function (shotPosition) {
						(shotPosition !== undefined).should.be.true;
						(shotPosition === 'a7').should.be.true;

						done();
					}
				}
			});

			parser.parse(messageBuffer);

		});
	});

	describe('BYE message', function() {
		it('should contain a player name', function (done) {

			// use 7 positions for our test case
			var messageBuffer = new Buffer('BYE PlayerName', 'utf-8');
			var parser = new Parser({
				client: {
					onBye: function () {
						done();
					}
				}
			});

			parser.parse(messageBuffer);

		});

		it('should receive a message in multiple buffers', function (done) {

			var bufferOne = new Buffer('BYE ', 'utf-8');
			var bufferTwo = new Buffer('PlayerName', 'utf-8');

			var parser = new Parser({
				client: {
					onBye: function () {
						done();
					}
				}
			});

			parser.parse(bufferOne);
			parser.parse(bufferTwo);

		});

		it('should ignore an invalid message start', function (done) {

			// use 7 positions for our test case
			var messageBuffer = new Buffer('asdasdasd#+23213BYE PlayerName', 'utf-8');
			var parser = new Parser({
				client: {
					onBye: function () {
						done();
					}
				}
			});

			parser.parse(messageBuffer);

		});
	});
});