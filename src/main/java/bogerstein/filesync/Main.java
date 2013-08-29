package bogerstein.filesync;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		final Map<String, String> parsedCommandLineArgs = CommandLineArgumentParser.parse(args);
		loadCommandLineArgsIntoSystemProperties(parsedCommandLineArgs);
		
		final ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("spring.filesync.xml");
		// The context opens file listeners, so we shouldn't hit the close
		appContext.close();
	}

	private static void loadCommandLineArgsIntoSystemProperties(Map<String, String> commandLineArgs) {
		for (Entry<String, String> commandLineArg : commandLineArgs.entrySet()) {
			System.setProperty(commandLineArg.getKey(), commandLineArg.getValue());
		}
	}
}
