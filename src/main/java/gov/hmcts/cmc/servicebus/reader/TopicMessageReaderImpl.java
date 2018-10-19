package gov.hmcts.cmc.servicebus.reader;

import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import gov.hmcts.cmc.servicebus.dto.Claim;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TopicMessageReaderImpl implements TopicMessageReader{

    @Transactional
    @JmsListener(destination = "roc-4510/Subscriptions/email.subscription", containerFactory = "topicListenerFactory")
    public void handleMessage(Message<Claim> claimMessage) throws InterruptedException, ServiceBusException {
        System.out.println("The message is " + claimMessage.getPayload().getClaim());
        System.out.println("The Id is " + claimMessage.getHeaders().getId());
        System.out.println("message headers :::" +claimMessage.getHeaders());

        for(String key :claimMessage.getHeaders().keySet()){
            System.out.println("The key is " + key);
            System.out.println("The valkue is " + claimMessage.getHeaders().get(key));

        }
    }
}
