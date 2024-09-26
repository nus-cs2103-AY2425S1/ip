package stobberi.command;

import stobberi.components.TaskList;
import stobberi.components.Ui;
import stobberi.stobberiexception.NoSuchTaskStobberiException;
import stobberi.stobberiexception.StobberiException;

/**
 * Represents a command that terminates the application.
 * This command sets the exit flag to true, indicating that the application should exit.
 */
public class ExitCommand extends Command {

    /**
     * Constructs a new {@code ExitCommand} with the specified {@link TaskList} and command details.
     *
     * @param taskList      The list of tasks (though not used in this command).
     * @param restOfCommand The rest of the command given, which should be empty to successfully execute this command.
     */
    public ExitCommand(TaskList taskList, String restOfCommand) {
        super(taskList, restOfCommand);
    }

    /**
     * Executes the command by setting the exit flag to true, which indicates that the application should terminate.
     * If there is any additional text in the command,
     * a {@link NoSuchTaskStobberiException} is thrown with a message asking
     * if the user wants to exit by simply saying 'bye'.
     *
     * @return A string indicating that the application is saying goodbye.
     * @throws StobberiException If additional text is provided in the command.
     */
    @Override
    public String execute() throws StobberiException {
        if (!getRestOfCommand().isEmpty()) {
            throw new NoSuchTaskStobberiException("Do you want to exit? Just say 'bye'!");
        }

        setExitTrue();
        return Ui.sayGoodbye();
    }
}
