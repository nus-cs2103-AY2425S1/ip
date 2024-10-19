package yapbot.tasks;

import yapbot.exceptions.YapBotException;

/**
 * Child class of Task with no additional fields.
 */
public class ToDo extends Task {

    /**
     * Creates a ToDo instance that is set to incomplete by default.
     *
     * @param description Details of the Task.
     * @throws YapBotException If task description and/or deadline is empty.
     */
    public ToDo(String description) throws YapBotException {
        super(description);
    }

    /**
     * Creates a ToDo instance and allows completion status to be initialised.
     * This is mostly used when creating the task from a saved format.
     *
     * @param description Details of the Task.
     * @param isDone Completion status the task will be initialized with.
     * @throws YapBotException If task description is empty.
     */
    public ToDo(String description, boolean isDone) throws YapBotException {
        super(description);
        this.setDone(isDone);
    }

    @Override
    public String saveTask() {
        return "T/" + super.saveTask();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
