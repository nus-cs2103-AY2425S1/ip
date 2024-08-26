package chatbot;
import chatbot.task.Task;

public class TaskStub extends Task {
    public TaskStub() {
        super("task", false);
    }

    @Override
    public void setIsDone(boolean isDone) {
        System.out.println("blah");
    }
}
