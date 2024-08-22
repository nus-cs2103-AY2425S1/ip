package exception;

public class TaskIndexOutOfRangeException extends BotException {
    public TaskIndexOutOfRangeException(int taskIndex, String taskSummary) {
        super("Index " + taskIndex + " is out of range!\n" + taskSummary);
    }
}
