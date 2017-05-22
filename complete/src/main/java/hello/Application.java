package hello;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	final static Logger logger = Logger.getLogger(Application.class);
    public static void main(String[] args) {
    	logger.info("Testando");
        //SpringApplication.run(Application.class, args);
    }
   
}
