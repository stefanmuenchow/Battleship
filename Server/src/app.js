var BattleshipServer = require('./BattleshipServer'); 

// define command line argument(s)
var argv = require('optimist')
    .usage('Usage: $0')
    .demand('p').alias('p', 'port').describe('p', 'port to listen on for new connections')
    .argv;

// create the server
var server = new BattleshipServer(argv.p);

console.log("server started");