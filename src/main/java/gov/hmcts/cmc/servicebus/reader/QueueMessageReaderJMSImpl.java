package gov.hmcts.cmc.servicebus.reader;

import com.google.gson.Gson;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import gov.hmcts.cmc.servicebus.dto.Claim;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Profile(value="!azure")
public class QueueMessageReaderJMSImpl implements QueueMessageReader{

    @Autowired
    private JmsTemplate jmsTemplate;

    private final Logger logger = LoggerFactory.getLogger(QueueMessageReaderJMSImpl.class);

    private final static Gson GSON = new Gson();


    @JmsListener(destination = "${cmc.servicebus.connection.queue.name}")
    public void handleMessage(Message<String> claimMessage) throws InterruptedException, ServiceBusException {
        MessageHeaders headers =  claimMessage.getHeaders();
        logger.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
        logger.debug("Claim : headers received : {}", headers);
        logger.debug("Calim : Claim Id received : {}", claimMessage.getPayload());
        logger.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++");

    }

    @Override
    public void registerMessageHandler(String queueName) throws InterruptedException, ServiceBusException {
        // do nothing.
    }
}
