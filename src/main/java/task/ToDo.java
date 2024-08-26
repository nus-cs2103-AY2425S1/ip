package task;

import exception.TaskNameEmptyException;

public class ToDo extends Task {
    public ToDo(Boolean isDone, String taskName) throws TaskNameEmptyException {
        super(isDone, taskName);
    }

    @Override
    public String getTxtSavedToFile() {
        return "T " + super.getTxtSavedToFile();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
