package mysutong;

/**
 * The {@code Parser} class interprets and executes user commands within the MySutong application.
 * It processes the input commands and delegates execution to the {@code TaskList} for task-specific operations.
 */
public class Parser {

    /**
     * Executes a user command by parsing it and delegating it to the {@code TaskList} to handle.
     *
     * @param fullCommand the raw input command from the user.
     * @param tasks the current task list to operate on.
     * @param ui the UI handler to provide feedback to the user.
     * @param storage the storage handler to save/load tasks from persistent storage.
     */
    public void executeCommand(String fullCommand, TaskList tasks, Ui ui, Storage storage) {
        String[] inputs = fullCommand.split(" ", 2);
        String command = inputs[0];

        try {
            tasks.handleCommand(command, inputs.length > 1 ? inputs[1].trim() : "", ui, storage);
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }
}
