package gravitas.command;

import gravitas.storage.Storage;
import gravitas.tasklist.TaskList;

/**
 * Represents a command to display help message.
 */
public class HelpCommand extends Command {

    private static final String HELP_MESSAGE = "Here are the list of commands you can use:\n"
            + "1. todo <task>: Adds a todo task to the task list.\n"
            + "2. deadline <task> /by <date> <time>: Adds a deadline task to the task list.\n"
            + "3. event <task> /from <date> <time> /to <date> <time>: Adds an event task to the task list.\n"
            + "4. list: Displays the list of tasks in the task list.\n"
            + "5. mark <task number>: Marks a task as done.\n"
            + "6. unmark <task number>: Marks a task as not done.\n"
            + "7. delete <task number>: Deletes a task from the task list.\n"
            + "8. find <keyword>: Finds tasks with the keyword in the task list.\n"
            + "9. help: Displays the list of commands you can use.\n"
            + "10. bye: Exits the program.\n"
            + "\nNote: <date> should be of dd/MM/yyyy and <time> in 24-hour format.\n";

    /**
     * Constructor for HelpCommand.
     */
    public HelpCommand() {

    }

    /**
     * Executes the command to display help message.
     *
     * @return Help message
     */
    @Override
    public String executeCommand(TaskList taskList, Storage storage) {
        return HELP_MESSAGE;
    }
}
