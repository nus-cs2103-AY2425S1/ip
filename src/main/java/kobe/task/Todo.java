package kobe.task;

import kobe.task.Task;

public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + name;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}