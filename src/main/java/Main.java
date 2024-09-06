import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
// import javafx.scene.control.Label; <-- only for Tut Part 1
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image; // image

import javafx.scene.layout.Region; // styling

import java.io.IOException;
import javafx.fxml.FXMLLoader;


public class Main extends Application {

    /*
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png")); //
    // relative to main/resources
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private Duke duke = new Duke(); // Computer Response Object


    /* Level 3a
    private void handleUserInput() {
        dialogContainer.getChildren().addAll(new DialogBox(userInput.getText(), userImage));
        userInput.clear();
    };*-

    // Level 3b
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = duke.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
               // new DialogBox(userText, userImage), <-- for level 3b
               // new DialogBox(dukeText, dukeImage) <-- for level 3b
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getDukeDialog(dukeText, dukeImage)
        );
        userInput.clear();
    }
    */
    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            // rendering
            Scene scene = new Scene(ap); // arguments varies
            stage.setScene(scene); // Setting the stage to show our scene
            fxmlLoader.<MainWindow>getController().setDuke(duke);  // inject the Duke instance
            stage.show(); // Render the stage.
        } catch (IOException e) {
            e.printStackTrace();
        }
    /*
        // Scene Initialisation.

        /* Level 1 *-
        // Label helloWorld = new Label("Hello World!"); // Creating a new Label control

        /* Level 2*-
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        /* Removed for Level 3*-
        // DialogBox dialogBox = new DialogBox("Hello!", userImage);
        // dialogContainer.getChildren().addAll(dialogBox);
        // (as we do not want it to be a static content). To make it dyanmic: event handlers:
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        /**
         * Creates a dialog box containing user input, and appends it to
         * the dialog container. Clears the user input after processing.
         *-

        // Styling
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        ^level 1 to 3*/

    }
}

