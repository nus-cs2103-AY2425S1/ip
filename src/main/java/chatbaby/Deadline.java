package chatbaby;

import chatbaby.Command;
import chatbaby.Storage;
import chatbaby.Task;
import chatbaby.TaskType;
import chatbaby.TaskList;
import chatbaby.Ui;
import chatbaby.Parser;
import chatbaby.ChatBabyException;
import chatbaby.Deadline;
import chatbaby.Event;
import chatbaby.ToDo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    private LocalDateTime deadline;

    public Deadline(String name, String deadline) {
        super(name);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.deadline = LocalDateTime.parse(deadline, formatter);
    }

    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public String toFileText() {
        return "D | " + (isDone ? "1" : "0") + " | " + name + " | "
                + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")) + ")";
    }
}
