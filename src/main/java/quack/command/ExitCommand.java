package quack.command;

import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import quack.Storage;
import quack.TaskList;
import quack.Ui;

/**
 * This class is responsible for handling the stopping of Quack.
 */
public class ExitCommand extends Command {

    /** Ui to handle all user display interactions */
    private Ui ui;
    /** To store all of the users tasks */
    private TaskList taskList;
    /** Sotrage object to load and save data */
    private Storage storage;

    /**
     * Creates a ExitCommand object.
     * @param quack The chatbot object quack.
     * @param taskList A list that stores all the tasks tracked by Quack.
     * @param storage Storage object to save and load data from the save file.
     */
    public ExitCommand(TaskList taskList, Storage storage, Ui ui) {
        super();
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    @Override
    public void prompt() {

        execute(null);
    }

    @Override
    public void execute(String input) {

        // Ensures that the input is not null as if it is null then Quack is not getting the input from the user
        assert(input != null);

        try {
            storage.saveData(taskList);
            this.completeCommand();
        } catch (IOException IoError) {
            ui.printExceptionMessage(IoError);
        } finally {

            ui.printFarewell();

            // Set a delay to exit the program
            PauseTransition exitDelay = new PauseTransition(Duration.seconds(3));
            exitDelay.setOnFinished(event -> Platform.exit());

            // Start the countdown
            exitDelay.play();
        }
    }
}
