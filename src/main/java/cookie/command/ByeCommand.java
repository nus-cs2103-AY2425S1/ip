package cookie.command;

import java.io.IOException;

import cookie.storage.Storage;
import cookie.task.TaskList;
import cookie.ui.Ui;


public class ByeCommand extends Command {

    /**
     * Executes the {@code ByeCommand}, which handles the termination of the program.
     * This method attempts to save the current state of the task list to storage, and then
     * returns a farewell message from the user interface.
     *
     * @param taskList the list of tasks that will be saved before the program exits
     * @param ui the user interface object used to print the farewell message
     * @param storage the storage object responsible for saving the current task list to a file
     * @return a string containing the farewell message to the user
     */
    @Override
    public String executeCommand(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.saveFile(taskList.getTaskArrayList());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ui.printQuit();
    }
}
