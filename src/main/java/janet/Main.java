package janet;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    // user and janet images
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/suguru_geto.jpg"));
    private Image janetImage = new Image(this.getClass().getResourceAsStream("/images/janet.png"));

    private VBox dialogContainer;       // contains information about the text and image
    private TextField text;             // contains information about the text (user input)
    private Janet janet = new Janet("janet.txt");  //


    @Override
    public void start(Stage primaryStage) {

        ScrollPane scrollPane = new ScrollPane();
        dialogContainer = new VBox();  // Vbox is inside ScrollPane
        // scroll down to end every time dialogContainer's height changes (auto scroll down)
        // attach a listener to heightProperty, when the heightProperty changes, execute the function.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        scrollPane.setContent(dialogContainer);

        Button send = new Button("Send");
        send.setOnMouseClicked((event) -> {
            handleUserInput();  // when the event occurs, the function will run
        });
        text = new TextField();
        text.setOnAction((event) -> {
            handleUserInput();
        });

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, text, send);  // ScrollPane, Button and TextField are on the same level

//       format the window
        // stage (Stage)
        primaryStage.setTitle("Janet from The Good Place");
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(600.0);
        primaryStage.setMinWidth(400.0);

        // mainLayout (AnchorPane)
        mainLayout.setPrefSize(400.0, 600.0);

        // scrollPane (ScrollPane)
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // dialogContainer
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        // userInput and send button (textField and button)
        text.setPrefWidth(325.0);
        send.setPrefWidth(55.0);

        // AnchorPane
        AnchorPane.setTopAnchor(scrollPane, 1.0);   // sets scrollPane to be 1 unit away from the top of AnchorPane

        AnchorPane.setBottomAnchor(send, 1.0);  // sets the send button to be 1 unit from bottom edge of AnchorPane
        AnchorPane.setRightAnchor(send, 1.0);   // sets the send button to be 1 unit from right edge of AnchorPane

        AnchorPane.setLeftAnchor(text , 1.0);   // sets the text field to be 1 unit from left edge of AnchorPane
        AnchorPane.setBottomAnchor(text, 1.0);  // sets the text field to be 1 unit from bottom edge of AnchorPane

        Scene scene = new Scene(mainLayout);    // setting the scene to be the label

        primaryStage.setScene(scene);   // setting up the stage to show the scene
        primaryStage.show();    // render the stage
    }

    private void handleUserInput() {
        // adds the text and image into the dialogContainer and clears the text after
        String userText = text.getText();                           // get the input from the user
        String janetText = janet.getResponse(text.getText());       // pass the input to janet, get response

        DialogBox userDialog = DialogBox.getUserDialog(userText, userImage);
        userDialog.setColor(Color.PAPAYAWHIP);  // set the background colour of userDialog

        DialogBox janetDialog = DialogBox.getDukeDialog(janetText, janetImage);
        janetDialog.setColor(Color.ALICEBLUE);   // set the background colour of dukeDialog

        dialogContainer.getChildren().addAll(
                userDialog,
                janetDialog
        );
        text.clear();
    }
}
