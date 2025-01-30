package star.command;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import star.main.Storage;
import star.main.TaskList;
import star.main.Ui;

/**
 * Represents the Command to exit the application.
 * Inherits from the Command class.
 */
public class ExitCommand extends Command {
    private static final String BYE_MSG = "bye bye! come by again soon :)\n";

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(BYE_MSG);
        PauseTransition delay = new PauseTransition(Duration.seconds(2.5));
        delay.setOnFinished(event -> Platform.exit()); // close the application after the delay
        delay.play();
        return ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
