package duck.commands;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

import duck.data.TaskList;
import duck.data.exception.DuckException;
import duck.storage.Storage;
import duck.ui.Ui;





/**
 * Represents a command to exit the application.
 * When executed, this command triggers a goodbye message and indicates that the application should terminate.
 */
public class ByeCommand extends Command {

    /**
     * Constructs a ByeCommand with the specified message.
     *
     * @param message The message associated with the command. Typically used for logging or debugging.
     */

    public ByeCommand(String message) {
        super(message);
    }

    /**
     * Executes the exit command by displaying a goodbye message through the provided Ui instance.
     *
     * @param tasks The list of tasks. This parameter is not used in this command
     *              but is required by the abstract method.
     * @param storage The storage system for saving and loading tasks. This parameter
     *                is not used in this command but is required by the abstract method.
     * @param ui The user interface for displaying messages to the user.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DuckException {
        super.execute(tasks, storage, ui);

        ui.showGoodbyeMessage();

        // Pause for 2 seconds before exiting
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> Platform.exit()); // Exits the JavaFX application
        pause.play();
    }

    /**
     * Indicates that this command signifies an exit operation.
     *
     * @return true, as this command is intended to exit the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
