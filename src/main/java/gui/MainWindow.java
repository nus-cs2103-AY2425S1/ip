package gui;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import applemazer.Applemazer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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

    private Applemazer applemazer;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image applemazerImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Applemazer instance */
    public void setApplemazer(Applemazer applemazer) {
        this.applemazer = applemazer;
        dialogContainer.getChildren().add(
                DialogBox.getApplemazerDialog(applemazer.getUi().greeting(), applemazerImage)
        );
        scrollPane.setBackground(new Background(new BackgroundFill(Color.HOTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
        dialogContainer.setBackground(new Background(new BackgroundFill(Color.HOTPINK,
                CornerRadii.EMPTY, Insets.EMPTY)));
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = applemazer.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getApplemazerDialog(response, applemazerImage)
        );
        userInput.clear();

        if (applemazer.shouldExit()) {
            AtomicInteger countdownTime = new AtomicInteger(3);
            Timer timer = new Timer();
            TimerTask countdown = new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        if (countdownTime.get() > 0) {
                            dialogContainer.getChildren().add(DialogBox.getApplemazerDialog("Shutting down in "
                                    + countdownTime.getAndDecrement() + "...", applemazerImage));
                        } else {
                            timer.cancel();
                            Platform.exit();
                        }
                    });
                }
            };

            timer.scheduleAtFixedRate(countdown, 700, 1000);
        }
    }
}
