package gov.hmcts.cmc.servicebus.handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microsoft.azure.servicebus.ExceptionPhase;
import com.microsoft.azure.servicebus.IMessage;
import com.microsoft.azure.servicebus.IMessageHandler;
import gov.hmcts.cmc.servicebus.StreamReader;
import gov.hmcts.cmc.servicebus.dto.Claim;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;


public class EmailMessageHandler implements IMessageHandler {

    private static Logger logger = LoggerFactory.getLogger(EmailMessageHandler.class);

    @Override
    public CompletableFuture<Void> onMessageAsync(IMessage message) {
        //Gson gson = new GsonBuilder().create();

        String messageBody = StreamReader.readStream(message.getBody());
        //Claim claimDetails = gson.fromJson(messageBody, Claim.class);

        logger.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
        logger.debug("Claim : Message Id : {}", message.getMessageId());
        logger.debug("Claim : Delivery count : {}", message.getDeliveryCount());
        logger.debug("Claim : Delivery count : {}", messageBody);

        //logger.debug("Calim : Claim Id received : {}", claimDetails.getId());
        //logger.debug("Calim : Claim details received : {}", claimDetails.getClaim());
        //logger.debug("Calim : Claim Poison Message : {}", claimDetails.getPoisonMessage());
        logger.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++");

        CompletableFuture<Void> completableFuture = new CompletableFuture<>();
        if(messageBody.contains("YES")){
            completableFuture.completeExceptionally(new RuntimeException("$$$$$$ I am a Poison Message %%%%%%%%%%%%%%"));
        }else{
            completableFuture.complete(null);
        }

 //       completableFuture.completeExceptionally(new Exception("Not feeling well, now"));


        return completableFuture;
    }

    @Override
    public void notifyException(Throwable exception, ExceptionPhase phase) {
        logger.error("******** Error While processing messaging ********" + exception.getMessage());
        logger.error("**** Exception phase ******" + phase.name());


    }
}
