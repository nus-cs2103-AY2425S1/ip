package tayoo;

import tayoo.exception.TayooException;
import tayoo.tasks.Task;

public class TaskStub extends Task {

    public TaskStub() {
        super("Return book", true);
    }

    @Override
    public boolean markAsDone() {
        return true;
    }

    @Override
    public boolean markAsNotDone() {
        return true;
    }

    @Override
    public boolean isCompleted() {
        return true;
    }

    @Override
    public String getTitle() {
        return "Return book";
    }

    @Override
    public String toString() {
        return "[X] Return book";
    }

    @Override
    public String toTxt() throws TayooException {
        return "Task | true | Return Book";
    }

}
