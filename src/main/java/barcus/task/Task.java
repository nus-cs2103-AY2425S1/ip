package barcus.task;

public class Task {
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

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public String convertToSavedString() {
        String status = this.isDone ? "1" : "0";
        return status + " | " + this.description;
    }

    /**
     * Returns whether the description contains the substring
     * @param toFind string to find
     * @return true if description contains the substring
     */
    public boolean containsSubstring(String toFind) {
        return this.description.toLowerCase().contains(toFind.toLowerCase());
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
