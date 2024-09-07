package duck.commands;

import duck.data.TaskList;
import duck.data.exception.DuckException;
import duck.storage.Storage;
import duck.ui.Ui;

/**
 * Represents a command that displays help information to the user.
 * The {@code HelpCommand} class provides a list of commands that the user can use
 * to interact with the application.
 */
public class HelpCommand extends Command {

    /**
     * The help string that contains the list of available commands and their usage.
     */
    private static final String HELP_STRING = """
            Quack! Seems like you need Duck's help.
            Here are the commands you can use:
            1. todo <description> - Add a todo task
            2. deadline <description> /by <date> - Add a deadline task
            3. event <description> /from <date> /to <date> - Add an event task
            4. list - List all tasks
            5. mark <task number> - Mark a task as done
            6. unmark <task number> - Mark a task as incomplete
            7. delete <task number> - Delete a task
            8. find <keyword> - Find tasks with a keyword
            9. on <date> - List tasks due on a specific date
            10. bye - Exit the application
            11. help - Show this help message
            """;

    /**
     * Constructs a new {@code HelpCommand} with the specified message.
     *
     * @param message The message associated with the help command.
     */
    public HelpCommand(String message) {
        super(message);
    }

    /**
     * Executes the help command, displaying the help message to the user.
     *
     * @param tasks   The list of tasks managed by the application.
     * @param storage The storage object for saving/loading tasks.
     * @param ui      The UI object used to interact with the user.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DuckException {
        super.execute(tasks, storage, ui);

        ui.showHelpMessage(HELP_STRING);
    }

    /**
     * Indicates whether this command should exit the application.
     *
     * @return {@code false}, since the help command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
