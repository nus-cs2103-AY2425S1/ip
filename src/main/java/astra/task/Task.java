package astra.task;

import astra.AstraException;

import java.util.function.Function;

public abstract class Task {
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT,
    }

    private boolean done;
    private final String name;

    public Task(String name) throws AstraException {
        if (name.isBlank()) {
            throw new AstraException("Task description cannot be empty.");
        }
        this.name = name;
        this.done = false;
    }

    public Task(boolean done, String name) {
        this.name = name;
        this.done = done;
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
        char status = done ? '1' : '0';
        return status + " | " + name;
    }

    @Override
    public String toString() {
        char status = done ? 'X' : ' ';
        return String.format("[%s] %s", status, name);
    }

    public void setDone(boolean status) {
        this.done = status;
    }
}
