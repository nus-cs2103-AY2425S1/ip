package task;
import prince.Prince;



import task.Task;

public class ToDoTask extends Task {

    public ToDoTask(String description) {
        super(description);
    }

    @Override
    public String printTask() {
        return "[T]" + super.printTask();
    }

    @Override
    public String printFileFormat() {
        return "T | " + (isDone ? 1 : 0) + " | " + description;
    }
}
