package command;

import components.Storage;
import components.TaskListHistory;
import components.Ui;
import exceptions.LightException;
import task.TaskList;

public class HelpCommand extends Command {
    public static final String HELP_MESSAGE =
            "Here are the list of commands you can use:\n"
                    + "1. todo <description> - Adds a todo task to the task list.\n"
                    + "2. deadline <description> /by <date(DD/MM/YYYY)> <time(24HR)> - Adds a deadline task to the task list.\n"
                    + "3. event <description> /from <date(DD/MM/YYYY)> <time(24HR)> /to <date(DD/MM/YYYY)> <time(24HR)> - Adds an event task to the task list.\n"
                    + "4. list - Lists all tasks in the task list.\n"
                    + "5. mark <task number> - Marks a task as done.\n"
                    + "6. unmark <task number> - Marks a task as undone.\n"
                    + "7. delete <task number> - Deletes a task from the task list.\n"
                    + "8. find <keyword> - Finds tasks with the keyword in the task list.\n"
                    + "9. undo - Undoes the previous command.\n"
                    + "10. redo - Redoes the previous command.\n"
                    + "11. bye - Exits the program.\n"
                    + "12. help - Shows the list of commands you can use.\n";

    public HelpCommand() {
        super();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, TaskListHistory taskListHistory) throws LightException {
        return ui.beautifyMessage(HELP_MESSAGE);
    }
}
