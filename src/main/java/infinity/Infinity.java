package infinity;

import infinity.command.Command;
import infinity.infinityexception.InfinityException;
import infinity.storage.Storage;
import infinity.tasklist.TaskList;
import infinity.ui.Ui;
import infinity.ui.components.DialogBox;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * This is the Infinity Bot that will run the program. Create a new instance and it will run.
 */
public class Infinity {

    private static TaskList botTasks;

    /**
     * Constructor for the Infinity class.
     * Initialises the bot and runs infinitely until "bye" command is given.
     *
     * @param dialogContainer The container to insert a new response.
     * @param botImage The image to display on the bot's side.
     */
    public Infinity(VBox dialogContainer, Image botImage) {

        if (botTasks == null) {
            try {
                botTasks = new TaskList(Storage.checkAndReadFile());
                dialogContainer.getChildren().addAll(
                        DialogBox.createBotDialog(Ui.botSays(String.format(
                                "Hello, I'm a dummy bot called %s\n%s",
                                Ui.BOT_NAME,
                                "What can I not do for you?")), botImage));
            } catch (InfinityException e) {
                dialogContainer.getChildren().addAll(DialogBox.createBotDialog(
                        "Loading of save file was unsuccessful: " + e.getMessage(), botImage));
            }
        }

    }

    /**
     * Runs the bot when supplied with a new user input.
     *
     * @param currentInput The new user input to process.
     * @return The message the bot should output.
     */
    public static String newUserInput(String currentInput) {
        try {
            return Command.findCommand(currentInput, botTasks);
        } catch (InfinityException e) {
            return e.getMessage();
        }
    }

}
