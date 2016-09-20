package knocking.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AkkaDemoApplication {

    public static void main(String[] args) throws Exception {
        ApplicationContext context =
                SpringApplication.run(AkkaDemoApplication.class, args);
    }
}
