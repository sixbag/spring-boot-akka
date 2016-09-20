package knocking.demo.config.extension;

import akka.actor.Extension;
import akka.actor.Props;
import knocking.demo.actor.SpringActorProducer;
import org.springframework.context.ApplicationContext;

public class SpringExtension implements Extension {

    private ApplicationContext applicationContext;

    public void initialize(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Props props(String actorBeanName) {
        return Props.create(SpringActorProducer.class,
                applicationContext, actorBeanName);
    }
}
