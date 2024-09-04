package chatbot.logic;
import chatbot.task.Task;

/**
 * Models a simplified version of the Task class, meant as a stub for testing
 */
public class TaskStub extends Task {

    /**
     * Constructor for the TaskStub class
     */
    public TaskStub() {
        super("task", false);
    }

    /**
     * Method meant to simulate the setIsDone method in the parent Task class
     * @param isDone Boolean value representing whether the task has been marked as done
     */
    @Override
    public void setIsDone(boolean isDone) {
        System.out.println("blah");
    }
}
