package bean.gui;

import bean.Bean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private Bean bean;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image beanImage = new Image(this.getClass().getResourceAsStream("/images/DaBean.png"));

    /**
     * Initializes the bean.gui.MainWindow, setting up necessary bindings.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Bean instance into the controller.
     *
     * @param b The Bean instance.
     */
    public void setBean(Bean b) {
        bean = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Bean's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bean.getResponse(input); // bean.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBeanDialog(response, beanImage)
        );
        userInput.clear();
    }
}
