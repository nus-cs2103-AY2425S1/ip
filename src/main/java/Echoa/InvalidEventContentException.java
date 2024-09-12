package echoa;

/**
 * InvalidDEventContentException is a class that encapsulates errors relating to event.
 * It extends from the class InvalidTaskContentException.
 */

public class InvalidEventContentException extends InvalidTaskContentException {
    public InvalidEventContentException() {
        super();
    }

    @Override
    public String getMessage() {
        return
        "Event has been inputted in the wrong format.\n" +
        "Please key in the following format:\n" +
        "event [task name] /[start day, time and/or date] /[start day, time and/or date]";
    }
}
