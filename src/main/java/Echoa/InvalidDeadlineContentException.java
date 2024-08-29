package Echoa;

/**
 * InvalidDeadlineContentException is a class that encapsulates errors relating to deadline.
 * It extends from the class InvalidTaskContentException.
 */

public class InvalidDeadlineContentException extends InvalidTaskContentException {
    public InvalidDeadlineContentException() {
        super();
    }

    @Override
    public String getMessage() {
        return
        "Please key in the following format:\n" +
        "deadline [task name] /[YYYY-MM-DD] [HH:MM]";
    }

}
