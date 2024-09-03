package ekud.ui;

import java.util.function.Supplier;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Represents the UI of EKuD when running in GUI mode.
 *
 * @author uniqly
 */
public class GraphicalUi extends Ui {
    /** A supplier that returns input from user */
    private final Supplier<String> inputFunction;

    /** The container which shows all messages read and output */
    private final VBox dialogContainer;

    /** A boolean flag if current buffer is from user input or ekud output */
    private boolean isInput = false;

    /** The image representing ekud */
    private Image ekudImage = new Image(this.getClass().getResourceAsStream("/images/upside-down.png"));

    /** The image representing the user */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/flushed.png"));

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
        String greeting = "Hey! My name is EkuD!!\nYou can call me Eku-Chan!";
        addToBuffer(greeting);
        printOutput();
    }

    @Override
    public void printGoodbye() {
        String goodbye = "I hope you enjoyed your stay!\nSee you next time! NOT!!";
        addToBuffer(goodbye);
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
