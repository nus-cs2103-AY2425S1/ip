package snowy;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Task {
    private boolean isCompleted = false;

    public final DateTimeFormatter FORMATTER = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);

    private String name;

    public Task(String name) {
        this.name = name;
    }

    public void markComplete() {
        this.isCompleted = true;
    }

    public void markIncomplete() {
        this.isCompleted = false;
    }

    public String toFileStorage() {
        String completeString = isCompleted ? "1" : "0";
        return completeString + "|" + name;
    }
    @Override
    public String toString() {
        String completeIcon = isCompleted ? "X" : " ";
        return "[" + completeIcon + "] " + this.name;
    }
}
