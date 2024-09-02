package fred;

import java.util.ArrayList;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    public String getDataFormat() {
        if (isDone) {
            return " | 1 | " + description;
        } else {
            return " | 0 | " + description;
        }
    }

    public boolean checkForKeyword(String keyword) {
        String[] descriptionParts = description.split(" ");
        for (String descriptionPart : descriptionParts) {
            if (descriptionPart.equals(keyword)) {
                return true;
            }
        }
        return false;
    }
}
