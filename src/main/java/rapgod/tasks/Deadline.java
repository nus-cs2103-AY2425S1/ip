package rapgod.tasks;

import rapgod.utils.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDateTime due;
    public Deadline(String description, String due) {
        super(description);
        this.due = Parser.parseToDateTime(due);
    }
    @Override
    public String toString() {
        String mark = isDone ? "X" : " ";
        String due = this.due.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma"));
        if(due.contains("12:00am")) {
            due = due.substring(0, due.length() - 8);
        }
        return String.format("[D] [%s] %s (by: %s)", mark, super.description, due);
    }
}
