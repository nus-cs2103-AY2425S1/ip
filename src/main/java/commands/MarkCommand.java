package commands;

import storage.Storage;
import storage.TaskList;
import ui.UI;

/**
 * Represents a command to mark or unmark a task as completed or incomplete.
 * The MarkCommand class handles the logic for marking tasks based on user input.
 */
public class MarkCommand implements Command {
    private final String action;
    private final String description;

    /**
     * Constructs a MarkCommand with the specified action and task description.
     *
     * @param action the action to perform ("mark" to mark as done, "unmark" to mark as not done)
     * @param description the description or index of the task to be marked/unmarked
     */
    public MarkCommand(String action, String description) {
        this.action = action;
        this.description = description;
    }

    /**
     * Executes the mark/unmark command, updating the task's completion status.
     * If the input is invalid (e.g., not a number or out of range), an error message is displayed.
     *
     * @param storage the Storage object for handling task persistence
     * @param master the TaskList object containing the list of tasks
     * @return false, indicating that the application should not terminate
     */
    @Override
    public boolean execute(Storage storage, TaskList master) {
        try {
            int index = Integer.parseInt(this.description);

            master.setTaskCompletion(this.action, index - 1);
            storage.saveList(master.getTasks());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Friday > Input the task number (1 - " + master.getSize() + ") to mark/unmark the task");
        }
        UI.printLine();
        return false;
    }
}
