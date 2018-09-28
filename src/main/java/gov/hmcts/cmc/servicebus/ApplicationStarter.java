package gov.hmcts.cmc.servicebus;

import gov.hmcts.cmc.servicebus.reader.QueueMessageReaderQClientImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
@ComponentScan(basePackages = {"gov.hmcts.cmc.servicebus"})
@SpringBootApplication
public class ApplicationStarter implements CommandLineRunner{

    private static final Logger logger = LoggerFactory.getLogger(ApplicationStarter.class);

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ApplicationStarter.class);
        logger.debug("The args received are {}",args);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.debug("The args received are {}",args);
        String queueName = args[0];
        if(args.length>1){
            queueName = queueName + "/$DeadLetterQueue";
        }

        logger.debug("Message Listener is to be registered for the Queue",queueName);
    }

}
