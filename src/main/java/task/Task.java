package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public abstract String getDatabaseString();

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this);
    }

    public void unmarkAsDone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }

    public static String getDateStringPrintFormat(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public static String getDateStringStorageFormat(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}