package knocking.demo.task;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import knocking.demo.config.extension.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @date 2016/9/20 18:54
 **/
@Component
public class TaskSupervisor {

    @Autowired
    ActorSystem system;

    @Autowired
    SpringExtension ext;

    @Bean(name = "supervisor")
    ActorRef supervisor(){
       return system.actorOf(
                ext.props("supervisor").withMailbox("akka.priority-mailbox"));
    }
}
