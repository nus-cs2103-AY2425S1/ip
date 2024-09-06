package sigmabot;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sigmabot.command.ChatCommand;
import sigmabot.command.GreetingCommand;
import sigmabot.command.ListOperation;
import sigmabot.command.TerminateCommand;

public class SigmaBot extends Application {

    private ChatCommand currentCommand;

    @Override
    public void start(Stage primaryStage) {
        TextArea displayArea = new TextArea();
        displayArea.setEditable(false);
        GreetingCommand.greet(displayArea);
        TextField inputField = new TextField();
        inputField.setPromptText("Type your command here...");

        Button sendButton = new Button("Send");
        sendButton.setOnAction(event -> {
            String input = inputField.getText().trim();
            inputField.clear();
            switch (input) {
            case "/list":
                currentCommand = new ListOperation();
                break;
            case "/exit":
                currentCommand = new TerminateCommand();
                break;
            default:
                displayArea.appendText("Unknown command. Please enter '/list' or '/exit'.\n");
                return;
            }
            if (currentCommand != null) {
                currentCommand.execute(displayArea, inputField);
            }
        });

        VBox root = new VBox(10, displayArea, inputField, sendButton);
        Scene scene = new Scene(root, 400, 600);

        primaryStage.setTitle("SigmaBot");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
