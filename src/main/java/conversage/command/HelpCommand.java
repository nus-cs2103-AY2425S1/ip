package conversage.command;

import conversage.exception.ConverSageException;
import conversage.storage.Storage;
import conversage.task.TaskList;
import conversage.ui.Ui;

/**
 * Represents a command to display help information.
 */
public class HelpCommand extends Command {

    private static final String HELP_MESSAGE =
            "Welcome, seeker of wisdom. Here are the commands that ConverSage understands:\n"
                    + "1. todo [task description] - Adds a ToDo task, a step on your journey.\n"
                    + "2. deadline [task description] /by [date] - Adds a Deadline task, marking the end of a quest.\n"
                    + "3. event [task description] /from [start time] /to [end time] - Adds an Event task, a moment in time to remember.\n"
                    + "4. list - Lists all tasks, revealing the path ahead.\n"
                    + "5. mark [task number] - Marks a task as done, a milestone achieved.\n"
                    + "6. unmark [task number] - Unmarks a task, revisiting a step.\n"
                    + "7. delete [task number] - Deletes a task, removing an obstacle from your path.\n"
                    + "8. find [keyword] - Finds tasks containing the keyword, seeking wisdom in your notes.\n"
                    + "9. bye - Exits the application, bidding you farewell for now.\n"
                    + "10. help - Displays this help message, guiding you on your journey.\n"
                    + "\nNote: For any timings, please use the format: yyyy-MM-dd HH:mm.\n"
                    + "May your path be clear and your tasks be light, seeker.";


    /**
     * Executes the help command, displaying the help message in the UI.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The UI to update.
     * @param storage The storage (not used in this command).
     * @return The help message.
     * @throws ConverSageException If an error occurs during execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ConverSageException {
        ui.showLine();
        ui.showMessage(HELP_MESSAGE);
        ui.showLine();
        return HELP_MESSAGE;
    }
}
