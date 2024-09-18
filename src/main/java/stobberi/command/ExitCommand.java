package stobberi.command;

import stobberi.components.Ui;
import stobberi.stobberiexception.NoSuchTaskStobberiException;
import stobberi.stobberiexception.StobberiException;

/**
 * Represents a command that terminates the application.
 * This command sets the exit flag to true, indicating that the application should exit.
 */
public class ExitCommand extends Command {
    /**
     * The rest of the command given.
     */
    private String restOfCommand;

    /**
     * Constructs a new {@code ExitCommand} and initializes the command with the exit flag set to false.
     *
     * @param restOfCommand The rest of the command given.
     */
    public ExitCommand(String restOfCommand) {
        this.restOfCommand = restOfCommand;
    }

    /**
     * Executes the command by setting the exit flag to true.
     * This indicates that the application should terminate.
     */
    @Override
    public String execute() throws StobberiException {
        if (!restOfCommand.isEmpty()) {
            throw new NoSuchTaskStobberiException("Do you want exit? Just say 'bye'!");
        }

        setExitTrue();
        return Ui.sayGoodbye();
    }
}
