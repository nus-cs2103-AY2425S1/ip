package rasputin.command;

import rasputin.task.RasputinException;

/**
 * Abstract class to represent commands to be executed in the chatbot.
 */
public abstract class Command {

    public abstract String execute() throws RasputinException;
    public abstract boolean isTerminated();

}
