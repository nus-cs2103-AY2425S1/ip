package myapp.command;

import myapp.core.Storage;
import myapp.task.TaskList;

/**
 * The {@code HelpCommand} class provides help information to the user.
 * It lists out all the available commands that the user can input and the correct format for using them.
 */
public class HelpCommand extends Command {

    /**
     * Constructs a {@code HelpCommand} object.
     */
    public HelpCommand() {
        super();
    }

    /**
     * Executes the help command by returning a string that lists out all the available commands
     * along with their format and an example of their usage.
     *
     * @param tasks   The current task list.
     * @param storage The storage object responsible for saving and loading tasks.
     * @return A string containing all the commands and their usage.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "These are the commands you can use:\n"

                // Task Creation
                + "Task Creation Commands:\n"
                + "1. todo <task> - Adds a todo task\n"
                + "   Example: todo Read a book\n"
                + "2. deadline <task> /by <d/M/yyyy HH:mm> - Adds a task with a deadline\n"
                + "   Example: deadline Submit assignment /by 10/09/2024 1300\n"
                + "3. event <task> /from <d/M/yyyy HH:mm> /to <d/M/yyyy HH:mm> "
                + "Adds an event with a start and end time\n"
                + "   Example: event Team meeting /from 10/09/2024 1300 /to 15/09/2024 1200\n"
                + "4. fixed <task> /period <timeInHours> - Adds a task with a fixed duration\n"
                + "   Example: fixed Work on project /period 3\n\n"

                // Task Viewing and Listing
                + "Task Viewing and Listing Commands:\n"
                + "5. list - Lists all tasks\n"
                + "6. list on <date> - Lists tasks on a specific date\n"
                + "   Example: list on 10/09/2024\n\n"

                // Task Management
                + "Task Management Commands:\n"
                + "7. mark <task number> - Marks the task as complete\n"
                + "   Example: mark 2\n"
                + "8. unmark <task number> - Unmarks the task (marks it as incomplete)\n"
                + "   Example: unmark 2\n"
                + "9. delete <task number> - Removes the task from the list\n"
                + "   Example: delete 3\n\n"

                // Task Search
                + "Task Search Commands:\n"
                + "10. find <keyword> - Finds all tasks containing the keyword\n"
                + "   Example: find book\n\n"

                // Program Exit
                + "Program Exit Command:\n"
                + "11. bye - Exits the program\n";
    }

    /**
     * Indicates whether this command will terminate the application.
     *
     * @return {@code false} as the help command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
