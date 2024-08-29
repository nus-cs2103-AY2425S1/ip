package yapbot.tasks;

import yapbot.exceptions.YapBotException;

/**
 * Parent class of all tasks that can be created for YapBot.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Returns a Task instance.
     *
     * @param description Details of the Task.
     */
    public Task(String description) throws YapBotException {
        if (description.isEmpty()) {
            throw new YapBotException("Error, Automated Task Suggestion module offline."
                    + "\nTask details must be manually entered.");
        }

        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the string representation of the task that is parseable by YapBot.
     */
    public String saveTask() {
        if (this.isDone) {
            return "1/" + this.description;
        } else {
            return "0/" + this.description;
        }
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}
