package knight2103.command;

import knight2103.Ui;
import knight2103.files.Storage;
import knight2103.tasks.TaskList;

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

    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
}
