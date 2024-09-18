package dudu.command;

import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 * Represents a command to wish user goodbye.
 */
public class ByeCommand extends Command {
    /**
     * Returns a goodbye message.
     *
     * @param taskList Task list containing the tasks.
     * @param ui User interface to interact with the user.
     * @param storage Storage to save tasks.
     */
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> Platform.exit());
        pause.play();
        return ui.getGoodbyeMessage();
    }
}
