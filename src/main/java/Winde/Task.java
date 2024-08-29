package Winde;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Task {
    protected String action;
    protected boolean complete;

    public Task(String action) {
        this.action = action;
        this.complete = false;
    }

    public Task(String action, boolean complete) {
        this.action = action;
        this.complete = complete;
    }

    public String isCompleted() {
        return (complete ? "X" : "O"); // mark done task with X
    }

    public void mark() {
        complete = true;
    }

    public void unmark() {
        complete = false;
    }

    public LocalDate getDate() {
        return LocalDate.now();
    }

    public String getAction() {
        return action;
    }

    @Override
    public String toString() {
        return (complete ? "X" : "O") + " | " + action;
    }
}

class Todos extends Task {

    public Todos(String action) {
        super(action);
    }

    public Todos(String action, boolean complete) {
        super(action, complete);
    }

    @Override
    public String toString() {
        return "T | " + super.toString();
    }
}

class Deadline extends Task {

    protected LocalDateTime date;

    public Deadline(String action, LocalDateTime date) {
        super(action);
        this.date = date;
    }

    public Deadline(String action, boolean complete, LocalDateTime date) {
        super(action, complete);
        this.date = date;
    }

    @Override
    public LocalDate getDate() {
        return this.date.toLocalDate();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "D | " + super.toString() + " | " + date.format(formatter);
    }
}

class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String action, LocalDateTime start, LocalDateTime end) {
        super(action);
        this.start = start;
        this.end = end;
    }

    public Event(String action, boolean complete, LocalDateTime start, LocalDateTime end) {
        super(action, complete);
        this.start = start;
        this.end = end;
    }

    @Override
    public LocalDate getDate() {
        return this.start.toLocalDate();
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return "E | " + super.toString() + " | " + start.format(dateFormatter)
                + " - " + end.format(dateFormatter);
    }
}

