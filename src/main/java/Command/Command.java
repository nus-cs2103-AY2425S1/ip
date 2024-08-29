package Command;

import Task.RasputinException;

/**
 * Abstract class to represent commands to be executed in the chatbot.
 */
public abstract class Command {

    public abstract void execute() throws RasputinException;
    public abstract boolean isTerminated();
}
