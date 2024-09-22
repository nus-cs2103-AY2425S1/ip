package chatbot.impl;

import chatbot.interfaces.ChatBot;
import chatbot.interfaces.MessageView;
import chatbot.interfaces.TaskStorage;

import java.util.Scanner;

public class AtlasGUI implements ChatBot {

    private final MessageView<Command> messageView;
    private final TaskStorage<Command> taskStorage;

    public AtlasGUI(Scanner scanner, MessageView<Command> messageView, TaskStorage<Command> taskStorage) {
        messageView.bind(scanner);
        this.messageView = messageView;
        this.taskStorage = taskStorage;
    }

    /**
     * <p>Starts the chatbot</p>
     */
    @Override
    public void start() {}

    @Override
    public String getResponse(String input) {

        Command command = messageView.parseInput(input);
        String[] inputParts = messageView.splitInput(input);

        return switch (command) {
            case Exit -> handleExit();
            case List -> handleList();
            case Mark -> handleMark(inputParts);
            case Unmark -> handleUnmark(inputParts);
            case Delete -> handleDelete(inputParts);
            case Find -> handleFind(inputParts);
            case ToDo, Event, Deadline -> handleAddTask(inputParts, command);
            case Unknown -> handleUnknown(inputParts);
            default -> "this is the default enum";
        };
    }

    private String handleExit() {
        return "Goodbye! Have a great day ahead!";
    }

    private String handleList() {
        String s = "Here are the items in your list:\n";
        return s + taskStorage.getTasks().toString();
    }

    private String handleMark(String[] inputParts) {
        return handleMarking(inputParts, true);
    }

    private String handleUnmark(String[] inputParts) {
        return handleMarking(inputParts, false);
    }

    private String handleMarking(String[] inputParts, boolean status) {
        String s = taskStorage.setTaskDone(inputParts, status).toString() + "\n";
        return s + taskStorage.saveTasks().toString();
    }

    private String handleDelete(String[] inputParts) {
        String s = taskStorage.deleteTask(inputParts).toString() + "\n";
        return s + taskStorage.saveTasks().toString();
    }

    private String handleFind(String[] inputParts) {
        return taskStorage.findTasks(inputParts).toString();
    }

    private String handleAddTask(String[] inputParts, Command command) {
        String s = taskStorage.addTask(inputParts, command).toString() + "\n";
        return s + taskStorage.saveTasks().toString();
    }

    private String handleUnknown(String[] inputParts) {
        return "Unknown command: " + inputParts[0];
    }
}