package neon;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    private String name;
    private boolean isCompleted;

    public Task(String name, boolean completed) {
        this.name = name;
        this.isCompleted = completed;
    }

    public String checkMark() {
        if (isCompleted) {
            return "X";
        } else {
            return " ";
        }
    }

    public String completeStatus() {
        if (isCompleted) {
            return "1";
        } else {
            return "0";
        }
    }

    public String processDate(String date) throws DateTimeException {
        try {
            LocalDate parsedDate = LocalDate.parse(date);
            return parsedDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeException d) {
            return date;
        }
    }

    public void check() {
        this.isCompleted = true;
    }

    public void uncheck() {
        this.isCompleted = false;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public abstract String toString();

    public abstract String toTask();
}
