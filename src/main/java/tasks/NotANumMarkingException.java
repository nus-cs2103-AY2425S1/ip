package tasks;

public class NotANumMarkingException extends RuntimeException {

    public NotANumMarkingException(int taskListNumber) {
        super(taskListNumber + " is not a numbered tasks.Task in the tasks.TaskList!");
    }
}
