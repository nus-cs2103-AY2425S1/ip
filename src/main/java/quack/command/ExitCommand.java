package quack.command;

import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import quack.util.Storage;
import quack.util.TaskList;
import quack.util.Ui;

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

        try {
            storage.saveData(taskList);
            this.completeCommand();
        } catch (IOException IoError) {
            ui.printExceptionMessage(IoError);
        } finally {
            this.endQuack();
        }
    }

    /**
     * Ends the chatbot application.
     * <p>
     */
    private void endQuack() {

        ui.printFarewell();

        // Set a delay to exit the program
        // Solution below inspired by
        // https://stackoverflow.com/questions/30543619/how-to-use-pausetransition-method-in-javafx
        PauseTransition exitDelay = new PauseTransition(Duration.seconds(3));
        exitDelay.setOnFinished(event -> Platform.exit());

        // Start the countdown
        exitDelay.play();
    }
}
