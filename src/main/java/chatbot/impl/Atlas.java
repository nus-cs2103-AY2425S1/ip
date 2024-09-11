package chatbot.impl;

import chatbot.interfaces.ChatBot;
import chatbot.interfaces.MessageView;
import chatbot.interfaces.TaskStorage;

import java.util.Scanner;

public class Atlas implements ChatBot {

    private final MessageView<Command> messageView;
    private final TaskStorage<Command> taskStorage;

    public Atlas(Scanner scanner, MessageView<Command> messageView, TaskStorage<Command> taskStorage) {
        messageView.bind(scanner);
        this.messageView = messageView;
        this.taskStorage = taskStorage;
    }

    /**
     * <p>Starts the chatbot</p>
     */
    @Override
    public void start() {
        messageView.introduce();
        taskStorage.loadTasks().showResult(messageView);

        while (true) {
            String input = messageView.listen();
            Command command = messageView.parseInput(input);
            String[] inputParts = messageView.splitInput(input);

            switch(command) {
            case Exit:
                handleExit();
                return;
            case List:
                handleList();
                break;
            case Mark:
                handleMark(inputParts);
                break;
            case Unmark:
                handleUnmark(inputParts);
                break;
            case Delete:
                handleDelete(inputParts);
                break;
            case ToDo:
            case Event:
            case Deadline:
                handleAddTask(inputParts, command);
                break;
            case Unknown:
                handleUnknown(inputParts);
                break;
            default:
                messageView.send("this is the default enum");
            }
        }
    }

    private void handleExit() {
        messageView.exit();
    }

    private void handleList() {
        messageView.send("Here are the items in your list:");
        taskStorage.getTasks().showResult(messageView);
    }

    private void handleMark(String[] inputParts) {
        handleMarking(inputParts, true);
    }

    private void handleUnmark(String[] inputParts) {
        handleMarking(inputParts, false);
    }

    private void handleMarking(String[] inputParts, boolean status) {
        taskStorage.setTaskDone(inputParts, status).showResult(messageView);
        taskStorage.saveTasks().showResult(messageView);
    }

    private void handleDelete(String[] inputParts) {
        taskStorage.deleteTask(inputParts).showResult(messageView);
        taskStorage.saveTasks().showResult(messageView);
    }

    private void handleAddTask(String[] inputParts, Command command) {
        taskStorage.addTask(inputParts, command).showResult(messageView);
        taskStorage.saveTasks().showResult(messageView);
    }

    private void handleUnknown(String[] inputParts) {
        messageView.send("Unknown command: " + inputParts[0]);
    }
}
