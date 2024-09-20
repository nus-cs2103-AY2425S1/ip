package chobo;


/**
 * The type Command.
 */
public abstract class Command {
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws InputException;
}
