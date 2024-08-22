package chatbot.impl;

import chatbot.MessageParser;
import chatbot.Task;
import chatbot.TaskStorage;
import chatbot.exceptions.InvalidMessageException;
import chatbot.impl.tasks.DeadlineTask;
import chatbot.impl.tasks.EventTask;
import chatbot.impl.tasks.TodoTask;

public class MessageParserImpl implements MessageParser {

    private final TaskStorage storage;

    public MessageParserImpl(TaskStorage storage) {
        this.storage = storage;
    }

    @Override
    public String handleMessage(String input) throws InvalidMessageException {
        if (input.isEmpty()) {
            throw new InvalidMessageException("Sorry, your message cannot be empty. :(");
        }

        String[] inputParts = input.split(" ", 2);
        String command = inputParts[0];

        return switch (command) {
            case "list" -> handleList();
            case "mark" -> handleMark(inputParts);
            case "unmark" -> handleUnmark(inputParts);
            case "todo" -> handleTodo(inputParts);
            case "deadline" -> handleDeadline(inputParts);
            case "event" -> handleEvent(inputParts);
            case "delete" -> handleDelete(inputParts);
            default -> throw new InvalidMessageException("Sorry, I don't recognize that command. :(");
        };
    }

    private String handleList() {
        return String.format("Here are your tasks:\n%s", storage);
    }

    // Todo: Potential combine these 2 methods?
    private String handleMark(String[] inputParts) throws InvalidMessageException {
        try {
            int taskIdx = Integer.parseInt(inputParts[1]) - 1;
            storage.setTaskAsDone(taskIdx);

            return String.format("Nice! Marked as done:\n%s", storage.getTask(taskIdx).toString());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidMessageException("Sorry, mark needs a numerical task index. :(");
        }
    }

    private String handleUnmark(String[] inputParts) throws InvalidMessageException {
        try {
            int taskIdx = Integer.parseInt(inputParts[1]) - 1;
            storage.setTaskAsNotDone(taskIdx);

            return String.format("Ah! Unmarked as not done:\n%s", storage.getTask(taskIdx).toString());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidMessageException("Sorry, unmark needs a numerical task index. :(");
        }
    }

    private String handleTodo(String[] inputParts) throws InvalidMessageException {
        try {
            Task task = new TodoTask(inputParts[1]);
            return addTask(task);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidMessageException("Sorry, todo needs a description. :(");
        }
    }

    private String handleDeadline(String[] inputParts) throws InvalidMessageException {
        try {
            String[] deadlineParts = inputParts[1].split("/by");
            Task task = new DeadlineTask(deadlineParts[0].trim(), deadlineParts[1].trim());

            return addTask(task);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidMessageException("Sorry, deadline needs a description and /by. :(");
        }
    }

    private String handleEvent(String[] inputParts) throws InvalidMessageException {
        try {
            String[] eventParts = inputParts[1].split("/from|/to");
            Task task = new EventTask(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim());

            return addTask(task);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidMessageException("Sorry, event needs a description, /from and /to. :(");
        }
    }

    private String addTask(Task task) {
        storage.addTask(task);
        return String.format("Got it. Task saved:\n%s\n%d tasks in the list.", task, storage.getSize());
    }

    private String handleDelete(String[] inputParts) throws InvalidMessageException {
        try {
            int taskIdx = Integer.parseInt(inputParts[1]) - 1;
            Task task = storage.getTask(taskIdx);
            storage.deleteTask(taskIdx);

            return String.format("Sure. Task deleted:\n%s\n%d tasks in the lst.", task, storage.getSize());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidMessageException("Sorry, delete needs a numerical task index. :(");
        }
    }

}
