package rizzler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a task that is stored in Rizzler's tasklist.
 */
class Task {
    protected final String name;
    protected boolean isDone;

    /**
     * Constructs a <code>Task</code> that is by default not done.
     *
     * @param name String that represents the task name.
     */
    Task(String name) {
        this.name = name;
        this.isDone = false;
    }
    protected Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Marks the task as done.
     */
    void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    void unmark() {
        this.isDone = false;
    }

    /**
     * Checks if the task name matches the given keyword.
     * 
     * @param keyword Keyword to match with the task name.
     * @return <code>true</Code> if the keyword is in the task name.
     */
    boolean matches(String keyword) {
        Pattern pattern = Pattern.compile(keyword);
        Matcher matcher = pattern.matcher(this.name);
        boolean isMatched = false;
        while (matcher.find()) {
            isMatched = true;
        }
        return isMatched;
    }

    /**
     * Returns a String representation of the task.
     *
     * @return String that represents the task.
     */
    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }

    /**
     * Returns a String representation of the task
     * to be saved by the file storage.
     *
     * @return String that represents the task in the save file.
     */
    String toSaveState() {
        return (this.isDone ? "1" : "0") + "/" + this.name + "\n";
    }
}
