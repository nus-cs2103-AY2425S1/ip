package vecrosen;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Handles receiving input and displaying output to/from the user.
 */
public class Ui extends AnchorPane {
    private Scanner input;
    private PrintStream output;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the UI
     *
     * @param inputStream Where the text the user inputs will be received
     * @param outputStream Where the text Vecrosen will respond with is sent
     */
    public Ui(InputStream inputStream, PrintStream outputStream) {
        input = new Scanner(inputStream);
        output = outputStream;
    }

    public Ui() {
        this(System.in, System.out);
    }

    /** Initializes the UI window */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Outputs a string with an indentation, to differentiate Vecrosen's dialog from the user's.
     *
     * @param s The string to be printed.
     */
    public void speak(String s) {
        System.out.println(s);
        dialogContainer.getChildren().add(DialogBox.getVecrosenDialog(s, dukeImage));
    }

    /**
     * Outputs an alert informing the user they have formatted their command incorrectly,
     * and elaborates on how they were supposed to use said command.
     *
     * @param usage The proper usage of the command.
     */
    public void alertInvalidFormat(String usage) {
        speak("Invalid format.");
        speak("Usage: " + usage);
    }

    /**
     * Prints the intro, followed by the tasks in the list.
     *
     * @param list The list of tasks to print
     * @param intro Sentence to print before the list of tasks
     * @param noTasks Sentence to print if the list is empty
     */
    public void printList(ArrayList<Task> list, ArrayList<Integer> indices, String intro, String noTasks) {
        if (list.isEmpty()) {
            speak(noTasks);
            return;
        }
        speak(intro);
        for (int i = 0; i < list.size(); ++i) {
            speak(indices.get(i) + "." + list.get(i).toString());
        }
    }

    /**
     * Obtains the next line that the user had entered.
     * TODO: Delete? Might be obsolete
     *
     * @return The line the user entered
     */
    public String readLine() {
        return input.nextLine();
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        Main.respond(input);
        userInput.clear();
        assert userInput.getText().isEmpty();
    }
}
