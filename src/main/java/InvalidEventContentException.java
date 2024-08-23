/**
 * InvalidDEventContentException is a class that encapsulates errors relating to event.
 * It extends from the class InvalidTaskContentException.
 */
public class InvalidEventContentException extends InvalidTaskContentException {
    public InvalidEventContentException() {
        super();
    }

    public String toString() {
        return
        "Please key in the following format:\n" +
        "event [task name] /from [start day, time and/or date] /to [start day, time and/or date]";
    }
}
