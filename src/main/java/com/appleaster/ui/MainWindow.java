package com.appleaster.ui;

import java.io.File;

import com.appleaster.main.Appleaster;
import com.appleaster.task.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Appleaster appleaster;

    // private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    // private Image appleasterImage = new Image(this.getClass().getResourceAsStream("/images/appleaster.jpg"));
    private Image userImage = new Image(new File("src/main/resources/images/user.png").toURI().toString());
    private Image appleasterImage = new Image(new File("src/main/resources/images/appleaster.jpg").toURI().toString());    

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setAppleaster(Appleaster a) {
        appleaster = a;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = appleaster.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAppleasterDialog(response, appleasterImage)
        );
        userInput.clear();
    }
}