package Talky;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that acts as Task
 */
public class Task {
    protected String name;
    protected boolean marked;

    /**
     * Constructor to initialize Task of given name.
     *
     * @param name Name of Task.
     */
    public Task(String name) {
        this.name = name;
        this.marked = false;
    }

    /**
     * Returns name of Task.
     *
     * @return Task name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set mark status of Task.
     *
     * @param marked Mark status.
     */
    public void setMark(boolean marked) {
        this.marked = marked;
    }

    /**
     * Returns save format name.
     *
     * @return Save format name.
     */
    public String toSaveFormat() {
        return "Talky.Task";
    }

    /**
     * Returns string of Task name and marked status.
     *
     * @return String of task name and marked status.
     */
    @Override
    public String toString() {
        String marked = this.marked ? "[X]" : "[ ]";
        return marked + name;
    }
}

class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "ToDo: " + super.toString();
    }

    @Override
    public String toSaveFormat() {
        String formatted = String.format("ToDo %s %b", this.name, this.marked);
        return formatted;
    }
}
class Deadline extends Task {
    protected LocalDateTime by;
    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }
    @Override
    public String toString() {
        String formattedBy = this.by.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        return "Deadline: " + super.toString() + " (by: " + formattedBy + ")";
    }

    @Override
    public String toSaveFormat() {
        String by = this.by.format(DateTimeFormatter.ofPattern("dd/MM/yyy HHmm"));
        String formatted = String.format("Deadline %s %s %b", this.name, by, this.marked);
        return formatted;
    }
}
class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString() {
        String formattedFrom = this.from.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        String formattedTo = this.to.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        return "Event: " + super.toString() + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }

    @Override
    public String toSaveFormat() {
        String from = this.from.format(DateTimeFormatter.ofPattern("dd/MM/yyy HHmm"));
        String to = this.to.format(DateTimeFormatter.ofPattern("dd/MM/yyy HHmm"));
        String formatted = String.format("Event %s %s %s %b", this.name, from, to, this.marked);
        return formatted;
    }
}