package chappy.util;

import java.io.IOException;
import java.util.Scanner;
import chappy.Chappy;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;

public class Gui extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    // private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    // private Image chappyImage =
    //         new Image(this.getClass().getResourceAsStream("/images/chappy.png"));
    private Chappy chappy = new Chappy();


    public Gui() {}


    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setChappy(chappy); // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    //     scrollPane = new ScrollPane();
    //     dialogContainer = new VBox();
    //     scrollPane.setContent(dialogContainer);

    //     userInput = new TextField();
    //     sendButton = new Button("Send");

    //     AnchorPane mainLayout = new AnchorPane();
    //     mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
    //     scene = new Scene(mainLayout);

    //     stage.setTitle("Chappy");
    //     stage.setResizable(false);
    //     stage.setMinHeight(600.0);
    //     stage.setMinWidth(400.0);
    //     mainLayout.setPrefSize(600.0, 800.0);
    //     scrollPane.setPrefSize(550, 750);
    //     scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    //     scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
    //     scrollPane.setVvalue(1.0);
    //     scrollPane.setFitToWidth(true);

    //     dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

    //     userInput.setPrefWidth(520.0);

    //     sendButton.setPrefWidth(65.0);

    //     AnchorPane.setTopAnchor(scrollPane, 1.0);

    //     AnchorPane.setBottomAnchor(sendButton, 1.0);
    //     AnchorPane.setRightAnchor(sendButton, 1.0);

    //     AnchorPane.setLeftAnchor(userInput, 1.0);
    //     AnchorPane.setBottomAnchor(userInput, 1.0);

    //     sendButton.setOnMouseClicked((event) -> {
    //         handleUserInput();
    //     });
    //     userInput.setOnAction((event) -> {
    //         handleUserInput();
    //     });

    //     stage.setScene(scene); // Setting the stage to show our scene
    //     stage.show(); // Render the stage.

    //     dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }
}
