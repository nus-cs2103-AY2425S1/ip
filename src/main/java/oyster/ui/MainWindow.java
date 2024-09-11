package oyster.ui;

import javafx.fxml.FXML;
<<<<<<< HEAD
=======
import javafx.scene.SnapshotParameters;
>>>>>>> branch-A-Assertions
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
<<<<<<< HEAD
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
=======
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
>>>>>>> branch-A-Assertions
import oyster.Oyster;
import oyster.ui.components.DialogBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Oyster oyster;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image chatbotImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Oyster instance */
    public void setOyster(Oyster o) {
        oyster = o;
    }

    /**
<<<<<<< HEAD
     * Creates two dialog boxes, one echoing user input and the other containing Oyster's reply and then appends them to
=======
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
>>>>>>> branch-A-Assertions
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        String[] response = oyster.readInput(input);
        StringBuilder stringBuilder = new StringBuilder();
<<<<<<< HEAD

        for (String s : response) {
            stringBuilder.append(s).append("\n");
        }

        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input.trim(), userImage),
            DialogBox.getChatbotDialog(stringBuilder.toString(), chatbotImage)
=======
        for (int i = 0; i < response.length; i++) {
            stringBuilder.append(response[i]).append("\n");
        }

        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input.trim(), getRoundedImage(userImage, 45)),
            DialogBox.getChatbotDialog(stringBuilder.toString(), getRoundedImage(chatbotImage, 45))
>>>>>>> branch-A-Assertions
        );

        userInput.clear();
    }
<<<<<<< HEAD
=======

    private static Image getRoundedImage(Image image, double radius) {
        Circle clip = new Circle(image.getWidth() / 2, image.getHeight() / 2, radius);
        ImageView imageView = new ImageView(image);
        imageView.setClip(clip);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        return imageView.snapshot(parameters, null);
    }
>>>>>>> branch-A-Assertions
}
