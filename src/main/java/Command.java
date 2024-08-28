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
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
    public abstract boolean isExit();
}
