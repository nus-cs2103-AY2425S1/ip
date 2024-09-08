package yapper.commands;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;
import yapper.components.Parser;
import yapper.components.Storage;
import yapper.components.TaskList;
import yapper.components.Ui;
import yapper.exceptions.YapperException;

/**
 * A class representing the command to terminate the program.
 */
public class TerminationCommand extends Command {
    public TerminationCommand() {
        super();
    }

    @Override
    public String execute(Parser parser, TaskList taskList, Storage storage) throws YapperException {
        String response = Ui.wrapText("Bye bye! :)");
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(1),
                event -> Platform.exit()
        ));
        timeline.setCycleCount(1);
        timeline.play();
        return response;
    }
}
