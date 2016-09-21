package knocking.demo.task;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import knocking.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @date 2016/9/20 18:48
 **/
@RestController
@RequestMapping(value = "/task")
public class TaskController {

    @Autowired
    ActorSystem system;

    @Autowired
    TaskRepository taskDAO;

    @Autowired
    SpringExtension springExtension;

    ActorRef supervisor;

    @RequestMapping(value = "/add")
    public Task insert() throws InterruptedException {
        final LoggingAdapter log = Logging.getLogger(system, "Application");

        if (supervisor == null)
            supervisor = system.actorOf(
                    springExtension.props("supervisor").withMailbox("akka.priority-mailbox"));

        Task task = new Task();
        task.setPayload(UUID.randomUUID().toString().replace("-", ""));
        task.setPriority(new Random().nextInt(99));
        supervisor.tell(task, null);

        return task;
    }

    @RequestMapping(value = "/all")
    public Iterable<Task> getAll() {
        List<Task> list = new ArrayList<Task>();
        Iterator<Task> iterator = taskDAO.findAll().iterator();
        while (iterator.hasNext())
            list.add(iterator.next());
        return list;
    }

    @RequestMapping(value = "/num")
    public int getSize() {
        List<Task> list = new ArrayList<Task>();
        Iterator<Task> iterator = taskDAO.findAll().iterator();
        while (iterator.hasNext())
            list.add(iterator.next());
        return list.size();
    }
}
