/**
 * InvalidTaskContentException is a class that encapsulates errors relating to task
 */
public class InvalidTaskContentException extends Exception {
    public InvalidTaskContentException() {
        super();
    }

    @Override
    public String getMessage() {
        return
        "Please key in the following format for different instructions.\n" +
        "TODO:\n" +
        "todo [task name]\n" +
        "DEADLINE:\n" +
        "deadline [task name] /[due day, time, and/or date]\n" +
        "EVENT\n" +
        "event [task name] /[start day, time and/or date] /[start day, time and/or date]";
    }
}
