package potong.task;

import potong.exceptions.IllegalInputPotongException;

public class ToDoTask extends Task {
    public ToDoTask(String description) throws IllegalInputPotongException {
        super(description);
    }

    public ToDoTask(String description, boolean isDone) throws IllegalInputPotongException {
        super(description, isDone);
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
