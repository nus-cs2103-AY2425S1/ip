package chobo;


/**
 * The type Command.
 */
public abstract class Command {
    /**
     * Returns string response of command.
     *
     * @param taskList Task list of user.
     * @param ui       Ui of user.
     * @param storage  Storage of user.
     * @return Response to command.
     * @throws InputException If input not correctly formatted.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws InputException;
}
