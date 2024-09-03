package edith.exception;

public class MissingEventDurationException extends Exception {
    public MissingEventDurationException() {
        super(" oops! event duration cannot be empty. please enter a duration after the task type and name." +
                " for example:\n\n" +
                "      event cs2101 project meeting /from 4pm /to 7pm");
    }
}
