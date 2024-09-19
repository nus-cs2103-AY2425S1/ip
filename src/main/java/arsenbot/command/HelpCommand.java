package arsenbot.command;

import arsenbot.storage.Storage;
import arsenbot.task.TaskList;
import arsenbot.task.TaskManagerException;
import arsenbot.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to display help information about available commands.
 */
public class HelpCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, TaskManagerException {

        return "Here are the available commands:\n"
                + "1. todo <description> - Adds a new ToDo task\n"
                + "2. deadline <description> /by <date> - Adds a new Deadline task\n"
                + "3. event <description> /from <start time> /to <end time> - Adds a new Event task\n"
                + "4. list - Lists all tasks\n"
                + "5. delete <index> - Deletes a task by index\n"
                + "6. mark <index> - Marks a task as done\n"
                + "7. unmark <index> - Marks a task as not done\n"
                + "8. find <keyword> - Finds tasks by keyword\n"
                + "9. undo - Undoes the last command\n"
                + "10. bye - Exits the application\n"
                + "11. help - Displays this help message";
    }

}
