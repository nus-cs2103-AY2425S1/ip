package talker.command;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import talker.Storage;
import talker.TalkerException;
import talker.Ui;
import talker.task.TaskList;

/**
 * Represents exit command to end current instance of chatbot
 */
public class ExitCommand extends Command {

    /**
     * Executes exit command
     *
     * @param list list of tasks to write into file
     * @param ui ui object to print output
     * @param storage storage object to write list into
     * @return String representing outcome of this event
     * @throws TalkerException if unable to write into file
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws TalkerException {
        storage.writeFile(list);
        // Create a PauseTransition to wait before closing application
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> {
            Platform.exit();
        });
        pause.play();
        return ui.printGoodBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
