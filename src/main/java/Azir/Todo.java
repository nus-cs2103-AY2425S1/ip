package Azir;

import Azir.Task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String formatText() {
        return String.format("T | %s | %s", super.getDoneString(), super.getDescription());
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
