package chatbot.impl;

import chatbot.interfaces.ChatBot;
import chatbot.interfaces.MessageView;
import chatbot.interfaces.TaskStorage;

import java.util.Scanner;

public class AtlasGUI implements ChatBot {

    private static final String GOODBYE_MESSAGE = "Goodbye! Have a great day ahead!";


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
            case List -> handleList(inputParts);
            case Mark -> handleMark(inputParts);
            case Unmark -> handleUnmark(inputParts);
            case Delete -> handleDelete(inputParts);
            case Find -> handleFind(inputParts);
            case ToDo, Event, Deadline -> handleAddTask(inputParts, command);
            case Unknown -> handleUnknown(inputParts);
        };
    }

    private String handleExit() {
        return GOODBYE_MESSAGE;
    }

    private String handleList(String[] inputParts) {
        boolean sorted = false;
        if (inputParts.length > 1 && inputParts[1].equals("sort")) {
            sorted = true;
        }
        try {
            String s = "Here are the items in your list:\n";
            return s + taskStorage.getTasks(sorted).toString();
        } catch (Exception e) {
            return "Error getting tasks: " + e.getMessage();
        }
    }

    private String handleMark(String[] inputParts) {
        return handleMarking(inputParts, true);
    }

    private String handleUnmark(String[] inputParts) {
        return handleMarking(inputParts, false);
    }

    private String handleMarking(String[] inputParts, boolean status) {
        try {
            String s = taskStorage.setTaskDone(inputParts, status).toString() + "\n";
            return s + saveTasks();
        } catch (Exception e) {
            return "Error marking task: " + e.getMessage();
        }
    }

    private String handleDelete(String[] inputParts) {
        try {
            String s = taskStorage.deleteTask(inputParts).toString() + "\n";
            return s + saveTasks();
        } catch (Exception e) {
            return "Error deleting task: " + e.getMessage();
        }
    }

    private String handleFind(String[] inputParts) {
        return taskStorage.findTasks(inputParts).toString();
    }

    private String handleAddTask(String[] inputParts, Command command) {
        try {
            String s = taskStorage.addTask(inputParts, command).toString() + "\n";
            return s + saveTasks();
        } catch (Exception e) {
            return "Error adding task: " + e.getMessage();
        }
    }

    private String handleUnknown(String[] inputParts) {
        return "Unknown command: " + inputParts[0];
    }

    // ---------------------- HELPER ----------------------
    private String saveTasks() {
        return taskStorage.saveTasks().toString();
    }
}