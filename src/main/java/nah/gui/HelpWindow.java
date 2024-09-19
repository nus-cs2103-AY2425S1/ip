package nah.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nah.Main;

/**
 * This class is to support a helping Window of the chatBot.
 */
public class HelpWindow extends AnchorPane {

    private final Image userImage =

            new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image helperImage =
            new Image(this.getClass().getResourceAsStream("/images/helper.png"));
    private final DialogBox greeting = DialogBox.getNahDialog(
            " This is Nah HelpBox. These are Nah's single word command"
                    + " 1.Bye : to exit the program\n"
                    + " 2.List : to list the tasks in the storage\n"
                    + " 3.Clean : to clean the storage\n"
                    + " Or type one of these key words to get the corresponding command format\n"
                    + " 1.Find : to find the matching tasks\n"
                    + " 2.DueOn : to find the uncompleted tasks that before due\n"
                    + " 3.Mark : to mark the corresponding task as done\n"
                    + " 4.Unmark : to mark the corresponding task as not done\n"
                    + " 5.Delete : to delete the task\n"
                    + " 6.Todo : to add a todo task\n"
                    + " 7.Deadline : to add a deadline task\n"
                    + " 8.Event : to add an event task\n"
                    + " Or type 'exit' to close Help Window ^:\n", helperImage);
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Stage stage;

    /**
     * Constructor
     */
    public HelpWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/HelpWindow.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            AnchorPane root = fxmlLoader.load();
            this.stage = new Stage();

            // title bar set up
            stage.setTitle("Nah Helper");
            stage.getIcons().add(new Image("/images/helper.png"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            dialogContainer.getChildren().addAll(this.greeting);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * initializing method
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void show() {
        stage.show();
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = HelpWindowResponse.responseTo(input);
        if (response.equals("exit")) {
            this.stage.close();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getNahDialog(response, helperImage)
        );
        userInput.clear();
    }
}
