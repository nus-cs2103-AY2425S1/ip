package babblebot.task;

public class Task {
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

    public void markAsUndone() {
        isDone = false;
    }

    public String toFileFormat() {
        if (getStatusIcon().equals("X")) {
            return "1" + " | " + this.description;
        } else {
            return "0" + " | " + this.description;
        }
    }

    /**
     * Returns the presence of the given keyword within the description.
     * "true" if the keyword is present, "false" if the keyword is not.
     *
     * @param keyword The supplied keyword string.
     * @return The boolean indicating presence of the keyword
     */
    public boolean hasKeyword(String keyword) {
        String[] descriptionArray = description.split(" ");
        for (String word : descriptionArray) {
            if (word.equals(keyword)) {
                return true;
            }
        }
        return false;

    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

