package colress.task;

import java.time.LocalDate;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public boolean fallsOnDate(LocalDate date) {
        return false;
    }

    @Override
    public String toString() {
        if (getIsDone()) {
            return String.format("[X][T] %s", getDescription());
        } else {
            return String.format("[ ][T] %s", getDescription());
        }
    }

    @Override
    public String toTextFile() {
        if (getIsDone()) {
            return String.format("[X] | To-Do | %s", getDescription());
        } else {
            return String.format("[ ] | To-Do | %s", getDescription());
        }
    }
}
