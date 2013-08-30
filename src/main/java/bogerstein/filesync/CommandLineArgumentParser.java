package bogerstein.filesync;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

public class CommandLineArgumentParser {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommandLineArgumentParser.class);
	public static void loadCommandLineArgsIntoSystemProperties(final String[] args) {
		if (args.length % 2 != 0) {
			throw new RuntimeException("Expected an even number of command line args, received " + args.length + ". " + Arrays.toString(args));
		}
		
		final Map<FileSyncProperty, String> commandLineArgs = parse(args);
		for (Entry<FileSyncProperty, String> commandLineArg : commandLineArgs.entrySet()) {
			LOGGER.debug("Loading property: " + commandLineArg.getKey().getKey());
			System.setProperty(commandLineArg.getKey().getKey(), commandLineArg.getValue());
		}
	}
	
	private static Map<FileSyncProperty, String> parse(final String[] args) {
		final Map<FileSyncProperty, String> parsedCommandLineArgs = Maps.newHashMap();
		for (int i = 0; i < args.length; i += 2) {
			final FileSyncProperty property = FileSyncProperty.getProperty(args[i]);
			parsedCommandLineArgs.put(property, args[i+1]);
		}
		return parsedCommandLineArgs;
	}
}
