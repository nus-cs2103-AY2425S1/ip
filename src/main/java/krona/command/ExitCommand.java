package krona.command;

import javafx.animation.PauseTransition;
import javafx.util.Duration;
import krona.storage.Storage;
import krona.task.TaskList;
import krona.ui.Ui;

/**
 * Represents a command to exit the Krona chatbot.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command by displaying a goodbye message to the user.
     *
     * @param tasks   The task list that the command operates on (not used in this command).
     * @param ui      The UI component that handles interactions with the user.
     * @param storage The storage component (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.goodbye(); // Show goodbye message

        // Create a PauseTransition to wait for 2 seconds before exiting
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> ui.exitApp()); // Exit the app after the delay
        pause.play(); // Start the pause
    }
}
