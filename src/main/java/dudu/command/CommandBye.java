package dudu.command;

import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 * Represents a bye user command into the chatbot
 */
public class CommandBye extends Command {
    /**
     * Executes the exit command by displaying a goodbye message to the user
     *
     * @param taskList The task list
     * @param ui The user interface to display the goodbye message
     * @param storage The storage
     * @return Bye response
     */
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> Platform.exit());
        pause.play();
        return ui.getGoodbyeMessage();
    }
}
