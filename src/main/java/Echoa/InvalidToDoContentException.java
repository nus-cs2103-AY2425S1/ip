package echoa;

/**
 * InvalidToDoContentException is a class that encapsulates errors relating to ToDo.
 * It extends from the class InvalidTaskContentException.
 */

public class InvalidToDoContentException extends InvalidTaskContentException {
    public InvalidToDoContentException() {
        super();
    }

    @Override
    public String getMessage() {
        return
        "ToDo has been inputted in the wrong format.\n" +
        "Please key in the following format:\n" +
        "todo [task name]";
    }
}
