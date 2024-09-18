package jude.command;

import jude.JudeException;
import jude.Storage;
import jude.TaskList;

/**
 * Represents the command which contains the instruction of series of actions to exit the Jude chatbot program.
 */
public class ExitCommand extends Command {

    /**
     * Exits the program. Sets the isExit to true, which is to be tracked in the main method of the chatbot.
     * @param list
     * @param storage
     * @throws JudeException if any one of the method call fails
     */
    @Override
    public void execute(TaskList list, Storage storage) {
        exit();
    }

    @Override
    public String toString() {
        return "ExitCommand";
    }
}
