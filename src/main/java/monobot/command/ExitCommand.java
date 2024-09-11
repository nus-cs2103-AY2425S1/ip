package monobot.command;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import monobot.util.Storage;
import monobot.util.TaskList;
import monobot.util.Ui;

/**
 * Represents bye command to end chat.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand to signal end of chat.
     */
    public ExitCommand() {
        super(CommandType.BYE);
    }

    /**
     * Executes bye command by exiting platform.
     *
     * @param tasks list of tasks containing task to mark.
     * @param ui ui object to handle output to user.
     * @param storage storage object to read/write file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printFarewell();
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }
}
