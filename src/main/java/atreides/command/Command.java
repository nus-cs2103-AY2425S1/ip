package atreides.command;

import atreides.task.TaskList;
import atreides.ui.AtreidesException;
import atreides.ui.Storage;
import atreides.ui.Ui;

/**
 * Encapsulates all the commands possible that the user can ask the chatbot
 */

public interface Command {

    void execute(TaskList tasks, Ui ui, Storage storage) throws AtreidesException;

    /**
     * Represents if this is the command to stop the program
     * @return whether the Ui will continue accepting commands after this
     */
    boolean isExit();
}
