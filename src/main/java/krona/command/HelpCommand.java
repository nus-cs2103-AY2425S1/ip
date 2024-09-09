package krona.command;

import krona.storage.Storage;
import krona.task.TaskList;
import krona.ui.Ui;

/**
 * Represents a command to display help information.
 */
public class HelpCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String helpMessage = "Here are the available commands:\n"
                + "1. list - Displays all tasks\n"
                + "2. todo [task] - Adds a todo task\n"
                + "3. deadline [task] /by [date] - Adds a task with a deadline\n"
                + "4. event [task] /from [date] /to [date] - Adds an event\n"
                + "5. done [task number] - Marks a task as done\n"
                + "6. delete [task number] - Deletes a task\n"
                + "7. find [keyword] - Finds tasks with the keyword\n"
                + "8. bye - Exits the program";

        ui.setCombinedMessage(helpMessage);
    }
}
