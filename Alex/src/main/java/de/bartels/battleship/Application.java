package de.bartels.battleship;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Application {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) throws IOException {
		logger.debug("Starting alex battleship client");
		
		// parse the application arguments
		final Options ops = createOptions();
		try {
			final CommandLineParser parser = new GnuParser();
			CommandLine cmd = parser.parse(ops, args);
			
			// check if the help message should be printed
			if(cmd.hasOption("help")) {
				printHelp(ops);
				return; // my dirty coding style
			}
			
			BattleshipBot.Builder botBuilder = new BattleshipBot.Builder();
			
			if(cmd.hasOption("port")) {
				botBuilder.port(Integer.parseInt(cmd.getOptionValue("port")));
			}
			
			if(cmd.hasOption("host")) {
				botBuilder.host(cmd.getOptionValue("host"));
			}
			
			if(cmd.hasOption("player-name")) {
				botBuilder.playerName(cmd.getOptionValue("player-name"));
			}
			
			if(cmd.hasOption("room-name")) {
				botBuilder.roomName(cmd.getOptionValue("room-name"));
			}
			
			if(cmd.hasOption("lineup-file")) {
				botBuilder.lineupFile(cmd.getOptionValue("lineup-file"));
			}
			
			BattleshipBot bot = botBuilder.build();
			bot.startup();
			
			System.out.print("Press the return key to shutdown the bot: ");
			System.in.read();
			bot.shutdown();
			System.out.println("Bot closed");
			
		} catch (ParseException | IllegalArgumentException | IllegalStateException e) {
			logger.error("unable to parse command line arguments: {}",  e.getMessage());
			System.out.println();
			printHelp(ops);
		}
	}
	
	@SuppressWarnings("static-access") // what a crazy builder implementation by apache o.O
	private static Options createOptions() {
		Options options = new Options();
		
		// add the port option
		options.addOption(OptionBuilder.withArgName("port").hasArg().withLongOpt("port")
				.withDescription("Port from the Battleship server to connect").create('P'));
		
		// add the host option
		options.addOption(OptionBuilder.withArgName("host").hasArg().withLongOpt("host")
				.withDescription("Host address from the Battleship server to connect").create('H'));
		
		// add the help option
		options.addOption(OptionBuilder.hasArg(false).withLongOpt("help")
				.withDescription("print this message").create('h'));
		
		// add the player name option
		options.addOption(OptionBuilder.withArgName("player name").hasArg().withLongOpt("player-name")
				.withDescription("defines the player name of this battleship bot").create('N'));
		
		// add the room name option
		options.addOption(OptionBuilder.withArgName("room name").hasArg().withLongOpt("room-name")
				.withDescription("the battelship bot joins to the room with this name").create('R'));
		
		// add the lineup file option
		options.addOption(OptionBuilder.withArgName("lineup file").hasArg().withLongOpt("lineup-file")
				.withDescription("the lineup definition file").create('L'));
		
		return options;
	}
	
	private static void printHelp(final Options ops) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp( "Application", ops );
	}
}
