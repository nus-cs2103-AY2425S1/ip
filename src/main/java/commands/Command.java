package commands;

import java.io.IOException;

import data.TaskList;
import data.Storage;
import ui.Ui;

/**
 * Represents a command that the program needs to execute
 */
public interface Command {
    /**
     * Method to execute the actions required for the command to accomplish
     *
     * @param taskList task list used by program to keep track of tasks
     * @param ui user interface used by program to portray information to user
     * @param storage to allow saving of user tasks
     * @return a boolean to let program know if it should continue running after command
     * @throws IOException if there was an issue with saving the data into the file
     */
    boolean execute(TaskList taskList, Ui ui, Storage storage) throws IOException;
}
