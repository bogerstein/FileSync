package bogerstein.filesync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		LOGGER.info("FileSync starting...");
		
		CommandLineArgumentParser.loadCommandLineArgsIntoSystemProperties(args);
		
		final ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("spring.filesync.xml");
		// The context opens file listeners, so we shouldn't hit the close
		appContext.close();
		
		LOGGER.info("FileSync shutting down...");
	}

	
}
