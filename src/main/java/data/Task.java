package data;

public abstract class Task {
    private boolean completed;
    private String description;

    public Task(String description, boolean isDone) {
        this.description = description;
        if (isDone) {
            markDone();
        } else {
            markNotDone();
        }
    }

    public void markDone() {
        this.completed = true;
    }

    public void markNotDone() {
        this.completed = false;
    }

    public String getSaveTaskString() {
        if (completed) {
            return "|1|" + description;
        }

        return "|0|" + description;
    }

    @Override
    public String toString() {
        if (completed) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }

    /**
     * Search task description to see if it contains given keyword
     * Ignores case
     *
     * @param searchStr keyword to search task description with
     * @return true if keyword found; else false
     */
    public boolean contains(String searchStr) {
        return this.description.toLowerCase().contains(searchStr.toLowerCase());
    }
}
