package pandabot.commands;

import pandabot.storage.Storage;
import pandabot.storage.TaskList;
import pandabot.ui.Ui;

/**
 * Represents a command to display the help message in the application.
 * This command provides the user with a list of all available commands and their descriptions.
 */
public class HelpCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return """
            PandaBot Commands:
            1. todo <description> : Adds a new Todo task.
            2. deadline <description> /by <DD/MM/YYYY HHmm> : Adds a new Deadline task.
            3. event <task description> /from <DD/MM/YYYY HHmm> /to <DD/MM/YYYY HHmm> : Adds a new Event task.
            4. list : Lists all tasks.
            5. mark <task number> : Marks the specified task as done.
            6. unmark <task number> : Unmarks the specified task.
            7. delete <task number> : Deletes the specified task.
            8. help : Displays this help message.
            9. find <keyword> : Lists tasks containing the keyword.
            10. bye : Exits the PandaBot.
            """;
    }
}
