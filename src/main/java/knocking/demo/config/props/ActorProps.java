package knocking.demo.config.props;

import akka.japi.Creator;

public class ActorProps {
    static class ConsumeActor implements Creator<ConsumeActor> {
        @Override public ConsumeActor create() {
            return new ConsumeActor();
        }
    }
}
