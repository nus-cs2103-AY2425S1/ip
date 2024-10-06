package wenjiebot.gui;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import wenjiebot.WenJie;

/**
 * Controller for the main GUI window. This class handles user interactions and updates
 * the dialog container with user input and bot responses.
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

    private WenJie wenJie;

    private Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
            "/images/jianbing.png")));
    private Image wenJieImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
            "/images/wenjie.png")));

    /**
     * Initializes the MainWindow controller. Binds the vertical scroll value of the scroll pane
     * to the height property of the dialog container to ensure the scroll pane updates as new content
     * is added.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greeting = "Eh wassup la bro, my name is Wen Jie.\n What you want? \nI got these commands for you, try"
                + " them out my guy:\n"
                + "1. todo <task>\n"
                + "2. event <task> /from <dd/mm/yyyy HHmm> /to <dd/mm/yyyy HHmm>\n"
                + "3. deadline <task> /by <dd/mm/yyyy HHmm>\n"
                + "4. list\n"
                + "5. mark <task index>\n"
                + "6. unmark <task index>\n"
                + "7. delete <task index>\n"
                + "8. find <keyword>\n"
                + "9. snooze <task no> /from <dd/mm/yyyy HHmm> /to <dd/mm/yyyy HHmm> OR "
                + "snooze <task no> /by <dd/mm/yyyy HHmm>\n"
                + "10. bye\n";
        dialogContainer.getChildren().addAll(
                DialogBox.getWenJieDialog(greeting, wenJieImage)
        );
    }

    /**
     * Injects the WenJie instance into the controller.
     *
     * @param wenJie the WenJie instance to be injected
     */
    public void setWenJie(WenJie wenJie) {
        this.wenJie = wenJie;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String wenJieText = wenJie.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getWenJieDialog(wenJieText, wenJieImage)
        );
        userInput.clear();
    }
}
