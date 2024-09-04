package rainy.database;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        scrollPane.setPrefSize(435, 600);
        userInput = new TextField();
        userInput.setPrefSize(350, 20);
        sendButton = new Button("send");
        AnchorPane anchorPane = new AnchorPane(scrollPane, userInput, sendButton);
        anchorPane.setPrefSize(435, 650);
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("RAINYBOT");
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        AnchorPane.setBottomAnchor(sendButton, 12.0);
        AnchorPane.setRightAnchor(sendButton, 12.0);
        AnchorPane.setBottomAnchor(userInput, 12.0);
        AnchorPane.setLeftAnchor(userInput, 12.0);
        stage.show();
    }
}