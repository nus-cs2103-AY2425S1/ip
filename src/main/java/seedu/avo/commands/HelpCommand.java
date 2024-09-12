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
                + "on <date> - search for tasks occuring on date\n\n"
                + "todo <name> - add a new todo type task\n\n"
                + "deadline <name> /by<date> - add a new deadline type task\n\n"
                + "event <name> /from<date> /to<date> - add a new event type task\n\n";
        return new CommandResult(helpMessage);
    }
}
