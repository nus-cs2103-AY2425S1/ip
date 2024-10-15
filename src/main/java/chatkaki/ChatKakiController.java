package chatkaki;

import chatkaki.commands.Command;
import chatkaki.tasks.TaskList;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;


/**
 * The controller for ChatKaki's GUI.
 */
public class ChatKakiController {

    @FXML
    private ListView<Message> chatListView;

    @FXML
    private TextField inputField;

    @FXML
    private TextArea taskArea;

    private ObservableList<Message> messages = FXCollections.observableArrayList();

    private Image userImage = new Image("/images/user.jpg");
    private Image botImage = new Image("/images/bot.png");

    /**
     * Initializes the controller.
     */
    @FXML
    public void initialize() {
        chatListView.setItems(messages);
        chatListView.setCellFactory(param -> new MessageCell());
        greetUser();
    }

    @FXML
    private void handleUserInput() {
        String userInput = inputField.getText();
        inputField.clear();
        taskArea.clear();

        // Add user message
        messages.add(new Message(userInput, true, userImage));

        Command command = Parser.parse(userInput);
        try {
            String output = command.execute();
            // Add bot response
            messages.add(new Message(output, false, botImage));
        } catch (Exception e) {
            messages.add(new Message("Error: " + e.getMessage(), false, botImage));
        }

        chatListView.scrollTo(messages.size() - 1);

        taskArea.appendText(getAllTasks());

        if (command.isExit()) {
            Platform.exit();
        }
    }

    private String getAllTasks() {
        assert TaskList.getTasks() != null : "TaskList array should not be null";
        StringBuilder listMessage = new StringBuilder();
        for (int i = 0; i < TaskList.getSize(); i++) {
            if (i > 0) {
                listMessage.append("\n");
            }
            listMessage.append(i + 1).append(". ").append(TaskList.getTask(i));
        }
        return listMessage.toString();
    }

    private void greetUser() {
        Storage.loadTasksFromFile();
        messages.add(new Message("Hello! I'm ChatKaki\nWhat can I do for you?", false, botImage));
        taskArea.appendText(getAllTasks());
    }



}

