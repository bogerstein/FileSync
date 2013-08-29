package bogerstein.filesync;

import java.util.Arrays;
import java.util.Map;

import com.google.common.collect.Maps;

public class CommandLineArgumentParser {

	private static final String REMOTE_DIRECTORY_OPTION = "-r"; // Required
	private static final String REMOTE_HOST_OPTION = "-h"; // Required
	private static final String REMOTE_USER_OPTION = "-u"; // Optional
	private static final String REMOTE_PASS_OPTION = "-p"; // Optional
	private static final String LOCAL_DIRECTORY_OPTION = "-l"; // Required
	
	public static Map<String, String> parse(final String[] args) {
		if (args.length % 2 != 0) {
			throw new RuntimeException("Expected an even number of command line args, received " + args.length + ". " + Arrays.toString(args));
		}
		
		final Map<String, String> parsedCommandLineArguments = Maps.newHashMap();
		for (String arg : args) {
			System.out.println(arg);
		}
		
		return parsedCommandLineArguments;
	}
}
