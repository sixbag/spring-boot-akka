package knocking.demo.task;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import knocking.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @date 2016/9/20 18:48
 **/
@RestController
@RequestMapping(value = "/task")
public class TaskController {

    @Resource(name = "supervisor")
    ActorRef supervisor;

    @Autowired
    ActorSystem system;

    @Autowired
    TaskRepository taskDAO;

    @RequestMapping(value = "/add")
    public Task insert() throws InterruptedException {

        final LoggingAdapter log = Logging.getLogger(system, "Application");

        Task task = new Task();
        task.setPayload("payload");
        task.setPriority(new Random().nextInt(99));
        supervisor.tell(task, null);

        while (!supervisor.isTerminated()) {
            Thread.sleep(100);
        }
        return task;
    }

    @RequestMapping(value = "/all")
    public Iterable<Task> getAll() {
        return taskDAO.findAll();
    }

    @RequestMapping(value = "/num")
    public Task getSize() {
        return taskDAO.findAll().iterator().next();
    }
}
