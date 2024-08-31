package chatkaki;

import chatkaki.commands.Command;
import chatkaki.commands.CommandList;
import chatkaki.tasks.TaskList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;



/**
 * The controller for ChatKaki's GUI.
 */
public class ChatKakiController {

    @FXML
    private TextArea chatArea;

    @FXML
    private TextField inputField;

    @FXML
    private TextArea taskArea;

    @FXML
    public void initialize() {
        greetUser();
    }

    private String getAllTasks() {
        StringBuilder listMessage = new StringBuilder();
        for (int i = 0; i < TaskList.getSize(); i++) {
            if (i > 0) {
                listMessage.append("\n");
            }
            listMessage.append(i + 1).append(". ").append(TaskList.getTask(i));
        }
        return listMessage.toString();
    }
    @FXML
    private void handleUserInput() {
        String userInput = inputField.getText();
        inputField.clear();
        chatArea.clear();
        taskArea.clear();
        chatArea.appendText("User: " + userInput + "\n\n");

        Command command = Parser.parse(userInput);
        try {
            String output = command.execute();
            chatArea.appendText("ChatKaki: " + output + "\n");
        } catch (Exception e) {
            chatArea.appendText("Error: " + e.getMessage() + "\n");
        }

        taskArea.appendText(getAllTasks());

        if (command.isExit()) {
            System.exit(0);
        }
    }

    private void greetUser() {
        Storage.loadTasksFromFile();
        chatArea.appendText("Hello! I'm ChatKaki\nWhat can I do for you?\n");
        taskArea.appendText(getAllTasks());
    }
}
