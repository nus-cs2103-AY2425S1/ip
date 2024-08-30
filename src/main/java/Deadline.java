package main.java;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDateTime dueDate;

    public Deadline(String name, String dueDate) {
        super(name);
        //param dueDate in the form "yyyy-mm-dd HHmm"
        this.dueDate = LocalDateTime.parse(dueDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        String s = super.toString();
        return "[D]" + s + String.format("(by: %s)", this.dueDate);
    }

    @Override
    public String toFileString() {
        int markedInt = this.isMarked() ? 1 : 0;
        return String.format("D | %d | %s | %s", markedInt, this.getName(), this.dueDate);
    }
}
