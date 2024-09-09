package command;

import java.io.IOException;

import exception.ScheduloException;
import task.TaskList;
import util.Storage;

/**
 * The HelpCommand class displays help information to the user.
 */
public class HelpCommand extends Command {

    /**
     * Executes the HelpCommand by displaying a list of available commands.
     *
     * @param tasks   The TaskList (not used in this command).
     * @param storage The Storage (not used in this command).
     * @return The help message string to display.
     * @throws ScheduloException This command does not throw any exceptions.
     * @throws IOException This command does not throw any IO exceptions.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws ScheduloException, IOException {
        return getHelpMessage();
    }

    /**
     * Returns the help message for the user.
     *
     * @return A string containing the list of available commands.
     */
    private String getHelpMessage() {
        return "Here are the available commands:\n" +
                "1. todo <task> - Adds a Todo task\n" +
                "2. deadline <task> /by <date> - Adds a Deadline task\n" +
                "3. event <task> /from <start_date> /to <end_date> - Adds an Event task\n" +
                "4. list - Shows the list of tasks\n" +
                "5. mark <index> - Marks a task as done\n" +
                "6. unmark <index> - Unmarks a task as not done\n" +
                "7. delete <index> - Deletes a task\n" +
                "8. find <keyword> - Finds tasks containing the keyword\n" +
                "9. help - Shows this help message\n" +
                "10. bye - Exits the application";
    }
}
