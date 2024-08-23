/**
 * InvalidDeadlineContentException is a class that encapsulates errors relating to deadline.
 * It extends from the class InvalidTaskContentException.
 */

public class InvalidDeadlineContentException extends InvalidTaskContentException {
    public InvalidDeadlineContentException() {
        super();
    }

    public String toString() {
        return
        "Please key in the following format:\n" +
        "deadline [task name] /by [due day, time, and/or date]";
    }

}
