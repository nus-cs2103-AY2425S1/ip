package llama.commands;

import java.io.IOException;

import llama.data.Storage;
import llama.data.TaskList;
import llama.ui.Ui;

/**
 * Represents a command that the program needs to execute
 */
public interface Command {
    /**
     * Executes the actions required for the command to accomplish
     *
     * @param taskList task list used by program to keep track of tasks
     * @param ui user interface used by program to portray information to user
     * @param storage to allow saving of user tasks
     * @return A string that represents Llama's response after command is completed
     * @throws IOException if there was an issue with saving the data into the file
     */
    String execute(TaskList taskList, Ui ui, Storage storage) throws IOException;
}
