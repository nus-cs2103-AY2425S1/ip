package astra.task;

import java.util.function.Function;

import astra.AstraException;

public abstract class Task {
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT,
    }

    private boolean isDone;
    private final String name;

    public Task(String name) throws AstraException {
        if (name.isBlank()) {
            throw new AstraException("Task description cannot be empty.");
        }
        this.name = name;
        this.isDone = false;
    }

    public Task(boolean isDone, String name) {
        this.name = name;
        this.isDone = isDone;
    }

    public static Task fromText(String text) {
        String[] data = text.split(" \\| ");
        Function<String, Boolean> toBool = x -> x.equals("1");

        switch (data[0]) {
        case "T":
            return new Todo(toBool.apply(data[1]), data[2]);
        case "D":
            return new Deadline(toBool.apply(data[1]), data[2], data[3]);
        case "E":
            return new Event(toBool.apply(data[1]), data[2], data[3], data[4]);
        default:
            throw new RuntimeException("Invalid file format.");
        }
    }

    public String toText() {
        char status = isDone ? '1' : '0';
        return status + " | " + name;
    }

    @Override
    public String toString() {
        char status = isDone ? 'X' : ' ';
        return String.format("[%s] %s", status, name);
    }

    public void setDone(boolean status) {
        this.isDone = status;
    }
}
