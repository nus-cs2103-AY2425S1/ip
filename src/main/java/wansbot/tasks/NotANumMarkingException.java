package wansbot.tasks;

public class NotANumMarkingException extends RuntimeException {

    public NotANumMarkingException(int taskListNumber) {
        super(taskListNumber + " is not a numbered wansbot.tasks.Task in the wansbot.tasks.TaskList!");
    }
}
