package Alex.Command;

import Alex.Storage.Storage;
import Alex.Task.TaskList;
import Alex.Ui.Ui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
/**
 * Command to exit the application.
 */
public class ExitCommand extends CommandBase {
    /**
     * Constructs an ExitCommand, setting the isExit flag to true.
     */
    public ExitCommand() {
        isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
        // Create a PauseTransition for 1 second delay
        PauseTransition delay = new PauseTransition(Duration.seconds(1));

        // After the delay, exit the application
        delay.setOnFinished(event -> {
            Platform.exit();  // Gracefully close the JavaFX application
            System.exit(0);   // Exit the program
        });

        delay.play();  // Start the delay
    }
}

