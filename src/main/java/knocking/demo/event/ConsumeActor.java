package knocking.demo.event;

import akka.actor.UntypedActor;
import org.apache.log4j.Logger;

public class ConsumeActor extends UntypedActor {

    private static Logger logger = Logger.getLogger(ConsumeActor.class);

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof String) {
            logger.info("Received String message: " + message);
            getSender().tell(message, getSelf());
        } else
            unhandled(message);
    }
}
