package task;

import exception.TaskNameEmptyException;

public class ToDo extends Task {
    public ToDo(String taskName) throws TaskNameEmptyException {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
