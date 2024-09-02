package megamind.gui;

import megamind.main.Megamind;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Megamind megamind;

    private final Image MegamindImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images" +
                                                                                                      "/Megamind.png")));
    private final Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/User.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setMegamind(Megamind m) {
        megamind = m;
        displayGreetingMessage();
    }

    private void displayGreetingMessage() {
        String greetingMessage = megamind.greet();
        dialogContainer.getChildren().addAll(
                DialogBox.getCookieDialog(greetingMessage, MegamindImage)
        );
    }
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = megamind.handleCommand(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getCookieDialog(response, MegamindImage)
        );
        userInput.clear();

        if ("See you around!".equals(response)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Goodbye!");
            alert.setHeaderText(null);
            alert.setContentText("Megamind is closing. See you next time!");
            alert.showAndWait();

            Stage stage = (Stage) dialogContainer.getScene().getWindow();
            stage.close();
        }
    }
}
