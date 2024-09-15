package scenebuilder;

import eli.Eli;
import eli.exception.EliException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import scenebuilder.DialogBox;

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

  private Eli eli;

  private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
  private Image eliImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

  @FXML
  public void initialize() {
    scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
  }

  /** Injects the Duke instance */
  public void setEli(Eli d) {
    eli = d;
  }

  /**
   * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
   * the dialog container. Clears the user input after processing.
   */
  @FXML
  private void handleUserInput() {
    String input = userInput.getText();
    String response = null;
    try {
      response = eli.getResponse(input);
    } catch (EliException e) {
      throw new RuntimeException(e);
    }
    dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getEliDialog(response, eliImage)
    );
    userInput.clear();
  }
}
