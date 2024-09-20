package jude.command;

import jude.JudeException;
import jude.Storage;
import jude.TaskList;

/**
 * Represents the instruction in series of action the program has to execute
 * which is implemented in the execute method.
 */
public abstract class Command {

    private boolean isExit;
    private String message = "";

    /**
     * Executes the task that has to be done by the command.
     * @param list Tasklist.
     * @param storage Save file handler.
     * @throws JudeException If any one of the method call fails.
     */
    public abstract void execute(TaskList list, Storage storage) throws JudeException;

    public void exit() {
        this.isExit = true;
    }

    public boolean isExit() {
        return isExit;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public abstract String getType();
}
