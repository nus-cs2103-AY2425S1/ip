package mortal_reminder.tasks;

// solution is adapted from the Course Level 3 extension

import mortal_reminder.io.FormattedPrinting;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description) {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getType() {
        return type;
    }

    public abstract String getDescription();

    public abstract String convertToFileFormat();

    public void markDone() {
        if (!this.isDone) {
            this.isDone = true;
            FormattedPrinting.printMarked(this);
        } else {
            FormattedPrinting.markError();
        }
    }

    public void markUndone() {
        if (this.isDone) {
            this.isDone = false;
            FormattedPrinting.printUnmarked(this);
        } else {
            FormattedPrinting.unmarkError();
        }
    }

    public boolean getIsDone() {
        return isDone;
    }
}
