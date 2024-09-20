package elon;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/** Controller for the main GUI. */
public class MainWindow extends AnchorPane {
  @FXML private ScrollPane scrollPane;
  @FXML private VBox dialogContainer;
  @FXML private TextField userInput;
  @FXML private Button sendButton;

  private Elon elon;
  private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
  private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Elon.png"));

  @FXML
  public void initialize() {
    scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    dialogContainer.getChildren().addAll(DialogBox.greetUser(dukeImage));
  }

  /** Injects the Elon instance */
  public void setElon(Elon e) {
    this.elon = e;
  }

  /**
   * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then
   * appends them to the dialog container. Clears the user input after processing.
   */
  @FXML
  private void handleUserInput() {
    String input = userInput.getText();
    String[] inputArr = input.split(" ");
    String response = elon.getResponse(inputArr);
    dialogContainer
        .getChildren()
        .addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getElonDialog(response, dukeImage));
    userInput.clear();
  }
}
