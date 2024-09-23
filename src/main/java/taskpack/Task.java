package taskpack;

import java.time.LocalDateTime;

/**
 * Parent class for tasks, has its own subclass tasks.
 */
public class Task {
    private boolean isMarked;
    private String name;

    /**
     * Constructor for Task, usually gets called by subclasses.
     * @param name Name of the task.
     */
    public Task(String name, boolean isMarked) {
        this.isMarked = isMarked;
        this.name = name;
    }

    protected void markAsCompleted() {
        this.isMarked = true;
    }

    protected void markAsIncomplete() {
        this.isMarked = false;
    }
    @Override
    public String toString() {
        String box;
        if (this.isMarked) {
            box = "[X]";
        } else {
            box = "[ ]";
        }
        return box + " " + this.name;
    }

    /**
     * Returns task completion status.
     * @return Boolean value on whether task is completed.
     */
    public boolean isMarked() {
        return this.isMarked;
    }

    /**
     * Returns task name.
     * @return Task name as a String.
     */
    public String getName() {
        return this.name;
    }

    public LocalDateTime getDueDate() {
        return null;
    }

    public String getStart() {
        return null;
    }

    public String getEnd() {
        return null;
    }


    /**
     * Returns a string that is more easily parseable by Parser when file is read upon start.
     * It takes the current task traits and stores them into a string. Similar to toString
     * method. Uses polymorphism, so this method body is a filler one, actual implementation
     * in the task subclasses.
     * @return The String to be saved into the write file
     */
    public String toParseableString() {
        return "hello";
    }
}
