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
                + "9. sort <parameter> - Sort tasks by a parameter: \n"
                + "   - desc/description: Sort by task description\n"
                + "   - date: Sort by task date\n"
                + "   - todo (t): Sort by todo tasks\n"
                + "   - deadline (d): Sort by deadline tasks\n"
                + "   - event (e): Sort by event tasks\n"
                + "10. help - Show all commands\n"
                + "11. bye - Exit Sigma";
    }
}
