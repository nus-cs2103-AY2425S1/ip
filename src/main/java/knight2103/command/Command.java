package knight2103.command;

import knight2103.Ui;
import knight2103.files.Storage;
import knight2103.tasks.TaskList;

/**
 * Models a command in a general manner.
 */
public abstract class Command {
    protected final CommandVerb verb;
    protected final String predicate;

    Command(CommandVerb verb, String predicate) {
        this.verb = verb;
        this.predicate = predicate;
    }

    Command(CommandVerb verb) {
        this(verb, "");
    }

    /**
     * Executes the Command which process the tasks and returns a message to be shown in the bot GUI.
     *
     * @param tasks The object storing the list of tasks found in the bot.
     * @param ui The user interface of the bot.
     * @param storage The object containing the file that saves the list of tasks.
     * @return The message to be shown by the bot in GUI after command execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
}
