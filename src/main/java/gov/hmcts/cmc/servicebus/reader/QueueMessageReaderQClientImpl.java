package gov.hmcts.cmc.servicebus.reader;

import com.google.gson.Gson;
import com.microsoft.azure.servicebus.ExceptionPhase;
import com.microsoft.azure.servicebus.IMessage;
import com.microsoft.azure.servicebus.IMessageHandler;
import com.microsoft.azure.servicebus.MessageHandlerOptions;
import com.microsoft.azure.servicebus.QueueClient;
import com.microsoft.azure.servicebus.ReceiveMode;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.primitives.MessagingFactory;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import gov.hmcts.cmc.servicebus.StreamReader;
import gov.hmcts.cmc.servicebus.handler.EmailMessageHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;


@Profile(value="azure")
@Component
public class QueueMessageReaderQClientImpl implements QueueMessageReader{

    @Value(value = "${cmc.servicebus.connection.queue.name}")
    private String queueName;

    private static final String connectionString = "Endpoint=sb://cmc-claim-spike.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=9TM/H+0fZB4szplsQxfd3oBim/oAyCioKoLW8k53uDI=";


    private static final boolean DEFAULT_AUTO_COMPLETE = false;
    private static final int DEFAULT_MAX_CONCURRENT_CALLS = 1;
    private static final int DEFAULT_MAX_RENEW_TIME_MINUTES = 5;
    private static final int DEFAULT_MESSAGE_WAIT_TIME_MINUTES = 1;

    private final static Gson GSON = new Gson();

    @PostConstruct
    public void registerListener()throws InterruptedException, ServiceBusException{
        registerMessageHandler(queueName);
    }

    @Override
    public void registerMessageHandler(String queueName) throws InterruptedException, ServiceBusException {
        QueueClient receiveClient = new QueueClient(new ConnectionStringBuilder(connectionString, queueName), ReceiveMode.PEEKLOCK);
        receiveClient.registerMessageHandler(new EmailMessageHandler(), new MessageHandlerOptions(DEFAULT_MAX_CONCURRENT_CALLS, DEFAULT_AUTO_COMPLETE, Duration.ofMinutes(DEFAULT_MAX_RENEW_TIME_MINUTES)));
    }

}
