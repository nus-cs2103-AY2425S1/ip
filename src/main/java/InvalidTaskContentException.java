/**
 * InvalidTaskContentException is a class that encapsulates errors relating to task
 */
public class InvalidTaskContentException extends Exception {
    public InvalidTaskContentException() {
        super();
    }

    public String toString() {
        return
        "Please key in the following format for different instructions.\n" +
        "TODO:\n" +
        "todo [task name]\n" +
        "DEADLINE:\n" +
        "deadline [task name] /by [due day, time, and/or date]\n" +
        "EVENT\n" +
        "event [task name] /from [start day, time and/or date] /to [start day, time and/or date]";
    }
}
