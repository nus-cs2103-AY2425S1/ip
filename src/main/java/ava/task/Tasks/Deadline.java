package ava.task.Tasks;

import ava.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    //TODO:ERROR HANDLING + extra tasks
    private LocalDateTime time;
    public Deadline(String title,String time) {
        super(title);
        this.time = LocalDateTime.parse(time);
    }

    public Deadline(String title, boolean done, String time) {
        super(title, done);
        this.time = LocalDateTime.parse(time);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Deadline: ");
        sb.append(super.toString());
        sb.append("[By :");
        sb.append(time.format(DateTimeFormatter.ISO_DATE_TIME));
        sb.append("]");
        return sb.toString();
    }
}
