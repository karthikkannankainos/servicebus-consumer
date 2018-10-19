package gov.hmcts.cmc.servicebus.reader;

import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import gov.hmcts.cmc.servicebus.dto.Claim;
import org.springframework.messaging.Message;

public interface TopicMessageReader {
    public void handleMessage(Message<Claim> claimMessage) throws InterruptedException, ServiceBusException;
}
