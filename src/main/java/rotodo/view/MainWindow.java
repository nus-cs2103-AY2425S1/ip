package rotodo.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import rotodo.RoTodo;
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

    private RoTodo roTodo;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image rotodoImage = new Image(this.getClass().getResourceAsStream("/images/RoTodo.png"));
    private String userName = "User";
    private String rotodoName = "RoTodo";

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the RoTodo instance */
    public void setRoTodo(RoTodo r) {
        roTodo = r;
    }

    /**
     * Display RoTodo's banner on start.
     *
     * @param name of user
     */
    public void onStart(String name) {
        this.userName = name;
        dialogContainer.getChildren().add(
            DialogBox.getRoTodoDialog(roTodo.getResponse(), rotodoImage, rotodoName)
        );
    }

    public void setUserName(String name) {
        userName = name;
    }

    /**
     * Wait before closing application on exit.
     */
    public void onExit() {
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    // do nothing
                }
                Platform.exit();
            }
        }.start();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (!input.equals("")) {
            String response = roTodo.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage, userName),
                    DialogBox.getRoTodoDialog(response, rotodoImage, rotodoName)
            );
            userInput.clear();
            if (roTodo.hasExited()) {
                this.onExit();
            }
        }
    }
}
