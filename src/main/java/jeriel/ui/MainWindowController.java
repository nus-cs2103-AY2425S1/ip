package jeriel.ui;

import jeriel.Jeriel;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class MainWindowController {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Jeriel jeriel; 

    private ImageView userImage = new ImageView("/images/user.png");
    private ImageView botImage = new ImageView("/images/bot.png");

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Handle enter key press
        userInput.setOnAction(event -> handleUserInput());
    }

    public void setJeriel(Jeriel j) {
        this.jeriel = j;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (!input.isEmpty()) {
            try {
                String response = jeriel.getResponse(input);
                dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage.getImage()),
                    DialogBox.getBotDialog(response, botImage.getImage())
                );
            } catch (Exception e) {
                dialogContainer.getChildren().add(DialogBox.getErrorDialog(e.getMessage()));
            } finally {
                userInput.clear();
            }
        }
    }
}
