package alfred.task;

import alfred.exception.AlfredException;

public class TaskStub extends Task {

    // Constructor for the stub
    public TaskStub(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "X" : " ") + " | " + description;
    }
}
