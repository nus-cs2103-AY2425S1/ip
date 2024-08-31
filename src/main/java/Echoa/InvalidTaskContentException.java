package Echoa;

/**
 * InvalidTaskContentException is a class that encapsulates errors relating to task
 */
public abstract class InvalidTaskContentException extends Exception {
    public InvalidTaskContentException() {
        super();
    }

    @Override
    public String getMessage() {
        return
        "Task has been inputted in the wrong format.\n" +
        "Please key in the following format for different instructions.\n" +
        "TODO:\n" +
        "todo [task name]\n" +
        "DEADLINE:\n" +
        "deadline [task name] /[due day, time, and/or date]\n" +
        "EVENT\n" +
        "event [task name] /[start day, time and/or date] /[start day, time and/or date]";
    }
}
