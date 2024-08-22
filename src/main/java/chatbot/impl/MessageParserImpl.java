package chatbot.impl;

import chatbot.MessageParser;
import chatbot.Task;
import chatbot.TaskStorage;
import chatbot.impl.tasks.DeadlineTask;
import chatbot.impl.tasks.EventTask;
import chatbot.impl.tasks.TodoTask;

public class MessageParserImpl implements MessageParser {

    private final TaskStorage storage;

    public MessageParserImpl(TaskStorage storage) {
        this.storage = storage;
    }

    @Override
    public String handleMessage(String input) {
        // Todo: Invalid command exception
        String[] inputParts = input.split(" ", 2);
        String command = inputParts[0];

        return switch (command) {
            case "list" -> handleList();
            case "mark" -> handleMark(inputParts);
            case "unmark" -> handleUnmark(inputParts);
            case "todo" -> handleTodo(inputParts);
            case "deadline" -> handleDeadline(inputParts);
            case "event" -> handleEvent(inputParts);
            // Todo: Invalid command exception
            default -> "";
        };
    }

    private String handleList() {
        return String.format("Here are your tasks:\n%s", storage);
    }

    // Todo: Potential combine these 2 methods?
    private String handleMark(String[] inputParts) {
        // Todo: Invalid command exception
        int taskIdx = Integer.parseInt(inputParts[1]) - 1;
        storage.setTaskAsDone(taskIdx);

        return String.format("Nice! Marked as done:\n%s", storage.getTask(taskIdx).toString());
    }

    private String handleUnmark(String[] inputParts) {
        // Todo: Invalid command exception
        int taskIdx = Integer.parseInt(inputParts[1]) - 1;
        storage.setTaskAsNotDone(taskIdx);

        return String.format("Ah! Unmarked as not done:\n%s", storage.getTask(taskIdx).toString());
    }

    private String handleTodo(String[] inputParts) {
        Task task = new TodoTask(inputParts[1]);
        return addTask(task);
    }

    private String handleDeadline(String[] inputParts) {
        String[] deadlineParts = inputParts[1].split("/by");
        Task task = new DeadlineTask(deadlineParts[0].trim(), deadlineParts[1].trim());
        return addTask(task);
    }

    private String handleEvent(String[] inputParts) {
        String[] eventParts = inputParts[1].split("/from|/to");
        Task task = new EventTask(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim());
        return addTask(task);
    }

    private String addTask(Task task) {
        storage.addTask(task);
        return String.format("Got it. Task saved:\n%s\n%d tasks in the list.",
                task, storage.getSize());
    }

}
