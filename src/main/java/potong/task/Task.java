package potong.task;

import potong.exceptions.IllegalInputPotongException;

/**
 * Represents a Task.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String tag = "";

    /**
     * Initialise a task with a task description.
     *
     * @param description String description.
     * @throws IllegalInputPotongException If the task input is wrong.
     */
    public Task(String description) throws IllegalInputPotongException {
        if (description.isEmpty()) {
            throw new IllegalInputPotongException("We cannot have an empty task!");
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Initialise a task including a boolean to show if the task is done.
     *
     * @param description String description.
     * @param isDone Whether the task is done or not.
     * @throws IllegalInputPotongException If the task input is wrong.
     */
    public Task(String description, boolean isDone, String tag) throws IllegalInputPotongException {
        if (description.isEmpty()) {
            throw new IllegalInputPotongException("We cannot have an empty task!");
        }
        this.description = description;
        this.isDone = isDone;
        this.tag = tag;
    }

    private String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Marks the task as done.
     *
     * @return String representation of this action.
     */
    public String mark() {
        this.isDone = true;
        return String.format("Nice! I've marked this task as done:\n %s", this);
    }

    /**
     * Unmark the task as not done.
     *
     * @return String representation of this action.
     */
    public String unmark() {
        this.isDone = false;
        return String.format("OK, I've marked this task as not done yet:\n %s", this);
    }

    /**
     * Tag the task.
     * @param tag String representation of the tag.
     * @return String representation of this action.
     * @throws IllegalInputPotongException When the tag is empty.
     */
    public String tag(String tag) throws IllegalInputPotongException {
        if (tag.isEmpty()) {
            throw new IllegalInputPotongException("We cannot have an empty tag!");
        }
        this.tag = "#" + tag;
        return String.format("OK, I've tagged this task as %s\n %s", tag, this);
    }

    /**
     * Remove the tag from the task.
     * @return String representation of this action.
     */
    public String untag() {
        this.tag = "";
        return String.format("OK, I've untagged this task\n %s", this);
    }

    /**
     * Get the type of the task. To be overridden by child classes.
     *
     * @return Empty String.
     */
    public String getType() {
        return "";
    }

    /**
     * Get the description of the task.
     *
     * @return String description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get the time related time of the task. (Deadline or duration)
     *
     * @return Empty String.
     */
    public String getTime() {
        return "";
    }

    /**
     * Show whether the task is done or not.
     *
     * @return String "1" if the task is done, and "0" if the task is not done.
     */
    public String getStatus() {
        if (this.isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * Get the tag for the task.
     * @return String representation of the tag.
     */
    public String getTag() {
        if (this.tag.isEmpty()) {
            return " ";
        } else {
            return this.tag;
        }
    }

    /**
     * Check if the task contains the keyword.
     *
     * @param keyword Keyword.
     * @return True if the task contains the keyword, False if not.
     * @throws IllegalInputPotongException If the keyword is empty.
     */
    public boolean findKeyword(String keyword) throws IllegalInputPotongException {
        if (keyword.isEmpty()) {
            throw new IllegalInputPotongException("The keyword cannot be empty!");
        }
        return this.description.contains(keyword);
    }

    /**
     * String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.getStatusIcon(), this.getTag(), this.description);
    }
}
