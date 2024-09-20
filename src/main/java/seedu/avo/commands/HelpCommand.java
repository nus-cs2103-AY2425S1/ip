package seedu.avo.commands;

import seedu.avo.exceptions.AvoException;

/**
 * Represents the command to provide help to new users
 */
public class HelpCommand extends Command {
    private static final HelpCommand INSTANCE = new HelpCommand();

    private HelpCommand() {}
    public static HelpCommand of() {
        return INSTANCE;
    }
    @Override
    public CommandResult execute(String userInput) throws AvoException {
        String helpMessage = "COMMANDS\n\n"
                + "list - list all tasks\n\n"
                + "mark <name> - mark a task\n\n"
                + "unmark <name> - unmark a task\n\n"
                + "delete <index> - delete a task\n\n"
                + "find <name> - search for tasks\n\n"
                + "on <yyyy-mm-dd> - search for tasks occuring on date\n\n"
                + "todo <name> - add a new todo type task\n\n"
                + "deadline <name> /by <yyyy-mm-dd HH:mm> - add a new deadline type task\n\n"
                + "event <name> /from <yyyy-mm-dd HH:mm> /to <yyyy-mm-dd HH:mm> - add a new event type task\n\n"
                + "exit - close the application\n\n"
                + "Visit https://zwezeya.github.io/ip/ for a detailed user guide";
        return new CommandResult(helpMessage, false);
    }
}
