package noosy.task;

import noosy.task.Task;

public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    public Todo(String name, boolean status) {
        super(name, status);
    }

    @Override
    public String storeInFile() {
        return String.format("T | %s | %s", super.storeInFile(), this.name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
