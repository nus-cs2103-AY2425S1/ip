package commands;

import storage.Storage;
import storage.TaskList;
import ui.UI;

/**
 * Represents a command to remove a task from the task list.
 * The RemoveCommand class handles the logic for removing tasks based on user input.
 */
public class RemoveCommand implements Command {
    private final String desc;

    /**
     * Constructs a RemoveCommand with the specified task description or index.
     *
     * @param desc the description or index of the task to be removed
     */
    public RemoveCommand(String desc) {
        this.desc = desc;
    }

    /**
     * Executes the remove command, deleting the specified task from the task list.
     * If the input is invalid (e.g., not a number or out of range), an error message is displayed.
     *
     * @param storage the Storage object for handling task persistence
     * @param master  the TaskList object containing the list of tasks
     * @return false, indicating that the application should not terminate
     */
    @Override
    public boolean execute(Storage storage, TaskList master) {
        try {
            int index = Integer.parseInt(this.desc);

            master.removeTask(index - 1);
            storage.saveList(master.getParent());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Friday > Input the task number (1 - " + master.getSize() + ") to remove the task");
        }
        UI.printLine();
        return false;
    }
}
