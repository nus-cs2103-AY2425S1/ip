import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import storage.Storage;
import task.MerchandiseManager;
import task.TaskList;

/**
 * The main class for the ChatterBox GUI application. This class handles the
 * initialization and configuration of the user interface, including the main window,
 * dialog boxes, user input handling, and task management.
 */
public class ChatterBoxGui extends Application {

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image chatterboxImage = new Image(this.getClass().getResourceAsStream("/images/ChatterBox.jpg"));
    private ChatterBoxResponse response = new ChatterBoxResponse();
    private ParserGui parser = new ParserGui();
    private TaskList taskList = new TaskList();
    private MerchandiseManager merchandiseList = new MerchandiseManager();
    private Storage storage = new Storage("data/tasks.txt");
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;


    @SuppressWarnings("checkstyle:Regexp")
    @Override
    public void start(Stage stage) {

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);


        stage.setTitle("ChatterBox");
        stage.setResizable(true);
        stage.setMinHeight(600);
        stage.setMinWidth(400);

        mainLayout.setPrefSize(400.0, 600);

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

        stage.setScene(scene);
        stage.show();

        dialogContainer.getChildren().addAll(DialogBox.getChatterBoxDialog(response.greetUser(), chatterboxImage));

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private void handleUserInput() {
        String userText = userInput.getText().trim();

        if (userText.equalsIgnoreCase("bye")) {
            String chatterboxText = parser.getResponse();
            dialogContainer.getChildren().add(DialogBox.getChatterBoxDialog(chatterboxText, chatterboxImage));
            Platform.exit();
        }
        parser.parseExecute(userText, taskList, merchandiseList, storage, response);

        String chatterboxText = parser.getResponse();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getChatterBoxDialog(chatterboxText, chatterboxImage));
        userInput.clear();
    }
}