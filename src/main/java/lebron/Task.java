package lebron;

import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public void reschedule(LocalDate newDate) {
        
    }

    public String getStatusIcons() {
        return done ? "X" : " ";
    }

    public void markAsDone() {
        this.done = true;
    }

    public void markAsUndone() {
        this.done = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getStatus() {
        return this.done;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcons(), this.description);
    }

    public String toFileString() {
        return "";
    }
}
