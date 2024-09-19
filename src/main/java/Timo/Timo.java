package Timo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Stack;


class Task {
    private String tasktype = "T";
    private boolean mark;
    private final String description;
    public Task(boolean mark, String description) {
        this.mark = mark;
        this.description = description;
    }

    public void markDone() {
        this.mark = true;
        return;
    }

    public void markUndone() {
        this.mark = false;
        return;
    }

    public String getTask() {
        return this.tasktype;
    }

    public String getStatusIcon() {
        return (this.mark ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}

class Todo extends Task {
    private final String tasktype = "T";


    public Todo(boolean mark, String val) {
        super(mark, val);
    }


    @Override
    public String toString() {
        return "[" + this.tasktype + "]" + super.toString();
    }
}

class Deadline extends Task {
    private final LocalDateTime date;
    private final String tasktype = "D";

    public Deadline(boolean mark, String val, LocalDateTime date) {
        super(mark, val);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[" + this.tasktype + "]" + super.toString()
                + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                + ")";
    }
}

class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;
    private final String tasktype = "E";

    public Event(boolean mark, String val, LocalDateTime from, LocalDateTime to) {
        super(mark, val);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[" + this.tasktype + "]" + super.toString()
                + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                + ")";
    }
}

class Tuple<T> {
    private String first;
    private T second;
    public Tuple(String first, T second) {
        this.first = first;
        this.second = second;
    }
    public String getFirst() {
        return this.first;
    }
    public T getSecond() {
        return this.second;
    }
}

class TimoException extends Exception {

    public TimoException(String errorMessage) {
        super(errorMessage);
    }

}
/**
 * main class Timo
 */
public class Timo {

    private final Storage storage;
    private TaskList tasks;
    private final UI ui;

    private final Stack<Tuple<Task>> commandList;

    private final Parser parser;

    /**
     * initialises Timo with ui, storage and parser
     * @param filepath the path to store the data
     */
    public Timo(String filepath) {
        ui = new UI();
        storage = new Storage(filepath);
        commandList = new Stack<>();
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            tasks = new TaskList();
        }
        parser = new Parser(ui, storage, tasks, commandList);
    }
    /**
     * get parser
     *
     */
    public Parser getParser() {
        return this.parser;
    }
}

