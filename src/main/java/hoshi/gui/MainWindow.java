package hoshi.gui;

import java.util.Objects;

import hoshi.ui.Ui;
import hoshi.Hoshi;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * Controller for the Hoshi's main GUI.
 */
public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Hoshi hoshi;
    private final Ui ui = new Ui();

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.JPG"));
    private final Image hoshiImage = new Image(this.getClass().getResourceAsStream("/images/Hoshi.JPG"));

    /**
     * Initializes GUi and initial message
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        userInput.setPromptText("Enter your input here...");

        String initialMessage = ui.initialize();

        dialogContainer.getChildren().add(DialogBox.getHoshiDialog(initialMessage, hoshiImage));
    }

    /**
     * Injects the Hoshi instance
     */
    public void setHoshi(Hoshi d) {
        hoshi = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = hoshi.run(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getHoshiDialog(response, hoshiImage)
        );
        userInput.clear();

        if (Objects.equals(response, "Bye, Hope to see you again soon! \n")) {

            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            // instruct to exit the application after the delay
            delay.setOnFinished(event -> Platform.exit());
            // start the delay
            delay.play();
        }
    }

    /**
     * Creates a new window with text indicating what commands are available in Hoshi chatbot.
     */
    @FXML
    private void handleTopRightButtonClick() {

        Stage mainStage = (Stage) dialogContainer.getScene().getWindow();

        Stage helpStage = new Stage();

        helpStage.initOwner(mainStage);

        helpStage.setTitle("Hoshi Help");

        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setText("Hoshi provides you with the following commands and examples!\n"
                + "1.) Add todo/deadline/event - Add deadline 2022-12-12\n"
                + "2.) Mark/Unmark - Mark 1\n"
                + "3.) Delete - Delete 1\n"
                + "4.) Find - Find 1\n"
                + "5.) List\n"
                + "6.) Bye");
        helpStage.setScene(new Scene(textArea, 400, 150));
        textArea.setStyle("-fx-background-color: #5f6366; -fx-padding: 5px; -fx-font-size: 12px;");
        Image image = new Image("/images/Hoshi.JPG");
        helpStage.getIcons().add(image);
        helpStage.show();
    }

}
