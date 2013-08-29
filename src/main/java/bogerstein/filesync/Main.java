package bogerstein.filesync;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		final ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("spring.filesync.xml");
		
		appContext.close();
	}

}
