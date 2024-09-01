package yihuibot;

/*
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.NoSuchElementException;
import java.util.Scanner;
*/

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
import yihuibot.exception.executable.ExecutableException;
import yihuibot.exception.parse.ParseException;
import yihuibot.exception.taskformat.IncorrectTaskFormatException;
import yihuibot.executable.Executable;
import yihuibot.executable.TaskModifier;
import yihuibot.storage.Storage;
import yihuibot.task.TaskList;
import yihuibot.ui.Ui;
*/

/**
 * Your friendly todolist bot.
 *
 * @author Toh Yi Hui A0259080A
 */
public class Main extends Application {
    // The name of this bot
    private static final String NAME = "YihuiBot";

    // Format for date time of task
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/smiling-cat.jpg"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/chad-cat.jpg"));

    /**
     * Display the GUI for YihuiBot.
     */
    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        DialogBox dialogBox = new DialogBox("Hello!", userImage);
        dialogContainer.getChildren().addAll(dialogBox);

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        // Styling scene
        stage.setTitle("YihuiBot");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Set stage
        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();
    }
}
