package gov.hmcts.cmc.servicebus.handler;

import com.microsoft.azure.servicebus.ExceptionPhase;
import com.microsoft.azure.servicebus.IMessage;
import com.microsoft.azure.servicebus.IMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;


public class EmailMessageHandler implements IMessageHandler {

    private static Logger logger = LoggerFactory.getLogger(EmailMessageHandler.class);

    @Override
    public CompletableFuture<Void> onMessageAsync(IMessage message) {
        logger.debug("Message received from EmailMessageHandler " + message.getMessageId());
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();
        completableFuture.completeExceptionally(new Exception("Not feeling well, now"));
        return completableFuture;
    }

    @Override
    public void notifyException(Throwable exception, ExceptionPhase phase) {
        logger.error("******** Error While processing messaging ********" + exception.getMessage());
        logger.error("**** Exception phase ******" + phase.name());


    }
}
