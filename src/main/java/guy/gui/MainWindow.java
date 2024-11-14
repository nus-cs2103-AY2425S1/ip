package guy.gui;

import guy.ThatOneGuy;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Representation of ThatOneGuy's main window.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField entry;
    @FXML
    private Button send;

    private ThatOneGuy guy;
    private Stage stage;

    private Image userImage = new Image(String.valueOf(this.getClass().getResource("/images/user.png")));
    private Image guyImage = new Image(String.valueOf(this.getClass().getResource("/images/guy.jpeg")));

    /**
     * Initialises a main window.
     */
    @FXML
    public void init() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Makes ThatOneGuy greet the user.
     * They aren't too happy about this.
     */
    @FXML
    public void sendIntro() {
        dialogContainer.getChildren().add(Dialog.getGuyDialog(guy.getGreeting(), guyImage));
    }

    /**
     * Sets the ThatOneGuy instance
     * @param g ThatOneGuy instance to be used
     */
    public void setGuy(ThatOneGuy g) {
        guy = g;
    }

    public void setStage(Stage s) {
        stage = s;
    }

    @FXML
    private void handleUserInput() {
        String input = entry.getText();
        String response = guy.getResponse(input);
        dialogContainer.getChildren().addAll(
                Dialog.getUserDialog(input, userImage),
                Dialog.getGuyDialog(response, guyImage)
        );
        entry.clear();

        if (!guy.isRunning()) {
            try {
                wait(3000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            stage.close();
            Platform.exit();
        }
    }

}
