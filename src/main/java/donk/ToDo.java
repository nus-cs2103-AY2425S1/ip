package donk;

import donk.task.Task;

public class ToDo extends Task {

    public ToDo (String description) {
        super(description, "T");
    }

    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String toFileSaveString() {
        return this.taskType + "|" + (this.isDone ? "1" : "0") + "|" + this.description;
    }

}
