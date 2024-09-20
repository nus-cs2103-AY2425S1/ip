package commands;

import storage.Storage;
import storage.TaskList;

/**
 * Represents a command to remove a task from the task list.
 * The RemoveCommand class handles the logic for removing tasks based on user input.
 */
public class RemoveCommand implements Command {
    private final String description;

    /**
     * Constructs a RemoveCommand with the specified task description or index.
     *
     * @param description the description or index of the task to be removed
     */
    public RemoveCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the remove command, deleting the specified task from the task list.
     * If the input is invalid (e.g., not a number or out of range), an error message is displayed.
     *
     * @param storage the Storage object for handling task persistence
     * @param master  the TaskList object containing the list of tasks
     * @return String that denotes a response that is displayed to the user
     */
    @Override
    public String execute(Storage storage, TaskList master) {
        String response;
        try {
            int index = Integer.parseInt(this.description);

            response = master.removeTask(index - 1);
            storage.saveList(master.getTasks());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return new InvalidCommand(description).execute(storage, master);
        }
        return response;
    }
}
