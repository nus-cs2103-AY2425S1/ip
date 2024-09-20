package trackbot.commands;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import trackbot.TrackBotStorage;
import trackbot.task.TrackList;
import trackbot.ui.Ui;

/**
 * Command to exit trackbot
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TrackList trackList, Ui ui, TrackBotStorage storage) {
        String message = ui.showBye();

        // Delay 5 seconds for the app to exit
        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();

        return message;
    }

    /**
     * Set boolean isExit to true to exit trackbot
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
