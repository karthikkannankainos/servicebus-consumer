package gov.hmcts.cmc.servicebus.reader;

import com.microsoft.azure.servicebus.primitives.ServiceBusException;

public interface QueueMessageReader {
    void registerMessageHandler(String queueName)throws InterruptedException, ServiceBusException;
}
