package ekud.ui;

import java.util.Objects;
import java.util.function.Supplier;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Represents the UI of EKuD when running in GUI mode.
 *
 * @author uniqly
 * @see Ui
 */
public class GraphicalUi extends Ui {
    private static final String EKUD_IMAGE_PATH = "/images/upside-down.png";
    private static final String USER_IMAGE_PATH = "/images/flushed.png";

    /** A supplier that returns input from user */
    private final Supplier<String> inputFunction;

    /** The container which shows all messages read and output */
    private final VBox dialogContainer;

    /** A boolean flag if current buffer is from user input or ekud output */
    private boolean isInput = false;

    /** The image representing ekud */
    private final Image ekudImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(EKUD_IMAGE_PATH)));

    /** The image representing the user */
    private final Image userImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(USER_IMAGE_PATH)));

    /**
     * Creates the gui for EKuD with the input function and output container.
     *
     * @param dialogContainer The ui element which text is printed to.
     * @param inputFunction The function that reads user input.
     */
    public GraphicalUi(VBox dialogContainer, Supplier<String> inputFunction) {
        this.dialogContainer = dialogContainer;
        this.inputFunction = inputFunction;
    }

    @Override
    public String readCommand() {
        String input = inputFunction.get();
        // print user input
        isInput = true;
        addToBuffer(input);
        printOutput();
        isInput = false;

        return input; // return input
    }

    @Override
    public void printGreeting() {
        addToBuffer(GREETING_MESSAGE);
        printOutput();
    }

    @Override
    public void printGoodbye() {
        addToBuffer(GOODBYE_MESSAGE);
        printOutput();
    }

    @Override
    public void printOutput() {
        String output = collectBuffer();
        if (isInput) {
            dialogContainer.getChildren().add(DialogBox.getUserDialog(output, userImage));
        } else {
            dialogContainer.getChildren().add(DialogBox.getEkudDialog(output, ekudImage));
        }
    }
}
