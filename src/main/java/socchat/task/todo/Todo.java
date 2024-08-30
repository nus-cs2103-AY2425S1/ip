package socchat.task.todo;

import socchat.task.Task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, Boolean isDone) {
        super(description, isDone);
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toSave() {
        return "T" + " | " + super.getDoneStatus() + " | " + super.getDescription();
    }
}
