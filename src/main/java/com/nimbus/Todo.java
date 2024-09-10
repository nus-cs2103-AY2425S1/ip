package com.nimbus;

/**
 * Todo is a task
 */
public class Todo extends Task {

    public Todo(String description) {
        this(description, false);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toFileFormat() {
        String isDoneString = this.isDone ? "1" : "0";
        String commandHeader = getTypeIcon().charAt(1) + "|" + isDoneString;
        return commandHeader + "|" + this.getDescription();
    }

    @Override
    public String toString() {
        return getTypeIcon() + super.toString();
    }

    @Override
    public String getTypeIcon() {
        return "[T]";
    }

    @Override
    public boolean contains(String keyword) {
        return getDescription().contains(keyword);
    }
}
