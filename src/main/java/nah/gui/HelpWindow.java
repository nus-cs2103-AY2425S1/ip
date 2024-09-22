package nah.gui;

import java.io.IOException;

import javafx.animation.PauseTransition;
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
import javafx.util.Duration;
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
            " Hello. This is Nah HelpBox. If you need more help on Nah command, type 'help'", helperImage);
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
        dialogContainer.prefWidthProperty().bind(scrollPane.widthProperty());
        dialogContainer.prefHeightProperty().bind(scrollPane.heightProperty());
    }

    public void show() {
        stage.show();
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = HelpWindowResponse.responseTo(input);
        if (input.toLowerCase().trim().equals("exit")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> this.stage.close());
            pause.play();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getNahDialog(response, helperImage)
        );
        userInput.clear();
    }
}
