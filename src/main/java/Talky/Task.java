package Talky;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String name;
    protected boolean marked;

    public Task(String name) {
        this.name = name;
        this.marked = false;
    }

    public String getName() {
        return this.name;
    }

    public void setMark(boolean marked) {
        this.marked = marked;
    }

    public String toSaveFormat() {
        return "Talky.Task";
    }

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
        return "Talky.ToDo: " + super.toString();
    }

    @Override
    public String toSaveFormat() {
        String formatted = String.format("Talky.ToDo %s %b", this.name, this.marked);
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
        return "Talky.Deadline: " + super.toString() + " (by: " + formattedBy + ")";
    }

    @Override
    public String toSaveFormat() {
        String by = this.by.format(DateTimeFormatter.ofPattern("dd/MM/yyy HHmm"));
        String formatted = String.format("Talky.Deadline %s %s %b", this.name, by, this.marked);
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
        return "Talky.Event: " + super.toString() + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }

    @Override
    public String toSaveFormat() {
        String from = this.from.format(DateTimeFormatter.ofPattern("dd/MM/yyy HHmm"));
        String to = this.to.format(DateTimeFormatter.ofPattern("dd/MM/yyy HHmm"));
        String formatted = String.format("Talky.Event %s %s %s %b", this.name, from, to, this.marked);
        return formatted;
    }
}