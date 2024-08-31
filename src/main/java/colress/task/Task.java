package colress.task;

import java.time.LocalDate;

public abstract class Task {
    private final String DESCRIPTION;
    private boolean isDone;

    public Task(String description) {
        this.DESCRIPTION = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.DESCRIPTION = description;
        this.isDone = isDone;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getDescription() {
        return DESCRIPTION;
    }

    public void check() {
        this.isDone = true;
    }

    public void uncheck() {
        this.isDone = false;
    }
    public abstract boolean fallsOnDate(LocalDate date);

    public abstract String toTextFile();
}
