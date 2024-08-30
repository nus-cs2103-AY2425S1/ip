package main.java;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDateTime from, to;

    public Event(String name, String from, String to) {
        super(name);

        System.out.println(from);
        System.out.println(to);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format("(from: %s to: %s)", this.from, this.to);
    }

    @Override
    public String toFileString() {
        int markedInt = this.isMarked() ? 1 : 0;
        return String.format("E | %d | %s | %s | %s", markedInt, this.getName(), this.from, this.to);
    }
}
