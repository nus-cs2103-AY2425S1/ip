package lebron;

import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void reschedule(LocalDate newDate) {
        
    }

    public String getStatusIcons() {
        return isDone ? "X" : " ";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcons(), this.description);
    }

    public String toFileString() {
        return "";
    }
}
