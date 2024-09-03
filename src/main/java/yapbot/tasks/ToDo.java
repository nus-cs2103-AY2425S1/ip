package yapbot.tasks;

import yapbot.exceptions.YapBotException;

/**
 * Child class of Task with no additional fields.
 */
public class ToDo extends Task {

    public ToDo(String description) throws YapBotException {
        super(description);
    }

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
