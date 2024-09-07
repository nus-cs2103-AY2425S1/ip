package jude.command;

import jude.JudeException;
import jude.Storage;
import jude.TaskList;
import jude.Ui;

/**
 * Represents the class which contains the series of actions to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Exit the program. Sets the isExit to true, which is to be tracked in the main method of the chatbot.
     * @param list
     * @param ui
     * @param storage
     * @throws JudeException, if any one of the method call fails
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        exit();
    }

    @Override
    public String toString() {
        return "ExitCommand";
    }
}
