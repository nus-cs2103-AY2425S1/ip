import controllers.InputParser;
import controllers.OutputHandler;
import controllers.commands.Command;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import lib.DbDriverInterface;
import lib.FileDbDriver;
import models.Message;
import models.TaskList;


public class Wojak extends Application {

    private InputParser parser = new InputParser();
    private DbDriverInterface dbDriver = new FileDbDriver();
    private TaskList taskList = new TaskList(dbDriver);

    @Override
    public void start(Stage stage) {

        ListView<Message> chatListView = new ListView<>();


        TextField userInput = new TextField();
        userInput.setPromptText("Type your command here...");

        OutputHandler outputHandler = new OutputHandler(chatListView);


        Button sendButton = new Button("Send");


        sendButton.setOnAction(e -> {
            String input = userInput.getText();
            if (!input.isEmpty()) {
                // Add user message to ListView
                chatListView.getItems().add(new Message(input, true));

                // Process user input using the existing Wojak logic
                try {
                    Command command = parser.parse(input);
                    command.execute(taskList, outputHandler);
                    chatListView.getItems().add(new Message("NOTICE: Command PARSED successfully. " +
                            "A successfully PARSED" +
                            "command does not mean the command was EXECUTED successfully. Any error messages" +
                            "will be thrown onto the GUI. Don't assume that the command was successful " +
                            "just because it was parsed successfully.", false));
                } catch (Exception ex) {
                    chatListView.getItems().add(new Message(ex.getMessage(), false));
                }

                // Added since some person said not having this is a problem
                chatListView.scrollTo(chatListView.getItems().size() - 1);

                // Clear the input field
                userInput.clear();
            }
        });

        // Set up the ListView to use a custom cell factory for displaying messages
        chatListView.setCellFactory(new Callback<ListView<Message>, ListCell<Message>>() {
            @Override
            public ListCell<Message> call(ListView<Message> param) {
                return new MessageCell();
            }
        });

        // Layout the components in a VBox
        VBox layout = new VBox(10, chatListView, userInput, sendButton);

        // Create the scene and set it on the stage
        Scene scene = new Scene(layout, 400, 400);
        stage.setScene(scene);

        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                sendButton.fire();
            }
        });

        stage.setTitle("Wojak Chatbot");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);  // Start the JavaFX application
    }
}

// Custom ListCell to display chat messages with avatar and alignment
class MessageCell extends ListCell<Message> {

    @Override
    protected void updateItem(Message message, boolean empty) {
        super.updateItem(message, empty);
        if (empty || message == null) {
            setGraphic(null);
            setText(null);
        } else {
            HBox messageBox = new HBox(10);
            messageBox.setFillHeight(true);

            // Load avatar image based on whether it's a user or bot message
            Image avatarImage = new Image(getClass().getResourceAsStream(message.isUser() ?
                    "/images/user_avatar.png" : "/images/bot_avatar.png"));
            ImageView avatarView = new ImageView(avatarImage);
            avatarView.setFitHeight(30);
            avatarView.setFitWidth(30);

            // Create a label that combines the sender name and message text
            Label messageLabel = new Label(message.getText());
            messageLabel.setWrapText(true);
            messageLabel.setMaxWidth(250);  // Limit the width of the message

            if (message.isUser()) {
                // Align user messages to the left (user avatar first, then message)
                messageBox.setAlignment(Pos.CENTER_RIGHT);
                messageBox.getChildren().addAll(messageLabel, avatarView);  // Avatar then message
            } else {
                // Align bot messages to the right (bot avatar first, then message)
                messageBox.setAlignment(Pos.CENTER_LEFT);
                messageBox.getChildren().addAll(avatarView, messageLabel);  // Avatar then message
            }

            // Allow message label to grow horizontally
            HBox.setHgrow(messageLabel, Priority.ALWAYS);

            setGraphic(messageBox);
        }
    }
}
