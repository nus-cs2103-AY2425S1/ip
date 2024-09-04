public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) throws EmptyDescriptionException {
        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        this.description = description;
        isDone = false;
    }

    public Task() {
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public String getTaskWithStatus() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }

    /**
     * Sets task as done.
     *
     * @return true if task completion status changed.
     */
    public boolean markTask() {
        if (!isDone) {
            isDone = true;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets task as undone.
     *
     * @return true if task completion status changed.
     */
    public boolean unmarkTask() {
        if (isDone) {
            isDone = false;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return getTaskWithStatus();
    }

    /**
     * Returns unique ID style to be read into txt file.
     * 
     * @return String indicating Task's details per chosen style.
     */
    public String toUString() {
        String s = "";
        s += isDone ? "1" : "0";
        return s;
    }

    protected static String getUniqueKey() {
        return "VSKXYudfs8162JDRoa619dvkat$2ndu$(!%$(@^nhdisg!))";
    }

}
