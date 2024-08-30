package Tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
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

    public boolean isCompleted() {
        return complete;
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