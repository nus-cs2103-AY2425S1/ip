package sigma.command;

import sigma.utils.Storage;
import sigma.utils.TaskList;
import sigma.utils.Ui;

/**
 * Represents the command to show all commands.
 */
public class HelpCommand extends Command {

    public HelpCommand(String[] commandArray) {
        super(commandArray);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Sigmas don't need help... but here are the commands you can use:\n"
                + "1. list - List all tasks\n"
                + "2. todo <task> - Add a todo task\n"
                + "3. deadline <task> /by <date> - Add a deadline task\n"
                + "4. event <task> /from <date> /to <date> - Add an event task\n"
                + "5. mark <task number> - Mark a task as done\n"
                + "6. unmark <task number> - Unmark a task (mark it as not done) \n"
                + "7. delete <task number> - Delete a task\n"
                + "8. find <query> - Find tasks with the query\n"
                + "9. help - Show all commands\n"
                + "10. bye - Exit Sigma";
    }
}
