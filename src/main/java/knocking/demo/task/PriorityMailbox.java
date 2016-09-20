package knocking.demo.task;

import akka.actor.ActorSystem;
import akka.dispatch.PriorityGenerator;
import akka.dispatch.UnboundedPriorityMailbox;
import com.typesafe.config.Config;

/**
 * @date 2016/9/20 17:13
 **/
public class PriorityMailbox extends UnboundedPriorityMailbox {

    public PriorityMailbox(ActorSystem.Settings settings, Config config) {

        // Create a new PriorityGenerator, lower priority means more important
        super(new PriorityGenerator() {

            @Override
            public int gen(Object message) {
                if (message instanceof Task) {
                    return ((Task) message).getPriority();
                } else {
                    // default
                    return 100;
                }
            }
        });

    }
}
