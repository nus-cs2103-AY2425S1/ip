package chatbot.impl;

import java.util.List;

import chatbot.MessageParser;
import chatbot.Task;
import chatbot.TaskStorage;
import chatbot.exceptions.InvalidMessageException;
import chatbot.impl.tasks.DeadlineTask;
import chatbot.impl.tasks.EventTask;
import chatbot.impl.tasks.TodoTask;

/**
 * Implements the MessageParser interface to handle various types of user messages.
 */
public class MessageParserImpl implements MessageParser {

    private final TaskStorage storage;

    /**
     * Constructs a MessageParserImpl with the specified task storage.
     *
     * @param storage The TaskStorage object for managing tasks.
     */
    public MessageParserImpl(TaskStorage storage) {
        assert storage != null : "Storage cannot be null";

        this.storage = storage;
    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation processes various commands including list, mark, unmark,
     * todo, deadline, event, and delete. It throws an InvalidMessageException for
     * unrecognized commands or invalid inputs.
     */
    @Override
    public String handleMessage(String input) throws InvalidMessageException {
        assert input != null : "Input cannot be null";

        if (input.isEmpty()) {
            throw new InvalidMessageException("Sorry, your message cannot be empty. :(");
        }

        String[] inputParts = input.split(" ", 2);
        String command = inputParts[0];

        return switch (command) {
            case "help" -> handleHelp();
            case "list" -> handleList();
            case "mark" -> handleMark(inputParts);
            case "unmark" -> handleUnmark(inputParts);
            case "todo" -> handleTodo(inputParts);
            case "deadline" -> handleDeadline(inputParts);
            case "event" -> handleEvent(inputParts);
            case "delete" -> handleDelete(inputParts);
            case "find" -> handleFind(inputParts);
            default -> throw new InvalidMessageException("Sorry, I don't recognize that command. :(");
        };
    }

    /**
     * Handles the 'help' command.
     *
     * @return A string containing all available commands and their descriptions.
     */
    private String handleHelp() {
        return """
                Available commands:
                help - Show this help message
                list - Show all tasks
                mark <index> - Mark a task as done
                unmark <index> - Mark a task as not done
                todo <description> - Add a todo task
                deadline <description> /by <deadline> - Add a deadline task
                event <description> /from <start time> /to <end time> - Add an event task
                delete <index> - Delete a task
                find <keyword> - Find tasks containing the keyword
                """;
    }

    /**
     * Handles the 'list' command.
     *
     * @return A string representation of all tasks in the storage.
     */
    private String handleList() {
        return String.format("Here are your tasks:\n%s", storage);
    }

    /**
     * Handles the 'mark' command to mark a task as done.
     *
     * @param inputParts The split input containing the command and task index.
     * @return A confirmation message with the marked task details.
     * @throws InvalidMessageException If the task index is invalid.
     */
    private String handleMark(String[] inputParts) throws InvalidMessageException {
        assert inputParts.length == 2 : "Input command should have 2 parts";

        try {
            int taskIdx = Integer.parseInt(inputParts[1]) - 1;
            storage.setTaskAsDone(taskIdx);

            return String.format("Nice! Marked as done:\n%s", storage.getTask(taskIdx).toString());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidMessageException("Sorry, mark needs a numerical task index. :(");
        }
    }

    /**
     * Handles the 'unmark' command to mark a task as not done.
     *
     * @param inputParts The split input containing the command and task index.
     * @return A confirmation message with the unmarked task details.
     * @throws InvalidMessageException If the task index is invalid.
     */
    private String handleUnmark(String[] inputParts) throws InvalidMessageException {
        assert inputParts.length == 2 : "Input command should have 2 parts";

        try {
            int taskIdx = Integer.parseInt(inputParts[1]) - 1;
            storage.setTaskAsNotDone(taskIdx);

            return String.format("Ah! Unmarked as not done:\n%s", storage.getTask(taskIdx).toString());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidMessageException("Sorry, unmark needs a numerical task index. :(");
        }
    }

    /**
     * Handles the 'todo' command to add a new todo task.
     *
     * @param inputParts The split input containing the command and task description.
     * @return A confirmation message with the added task details.
     * @throws InvalidMessageException If the task description is missing.
     */
    private String handleTodo(String[] inputParts) throws InvalidMessageException {
        assert inputParts.length == 2 : "Input command should have 2 parts";

        try {
            Task task = new TodoTask(inputParts[1]);
            return addTask(task);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidMessageException("Sorry, todo needs a description. :(");
        }
    }

    /**
     * Handles the 'deadline' command to add a new deadline task.
     *
     * @param inputParts The split input containing the command and command body.
     * @return A confirmation message with the added task details.
     * @throws InvalidMessageException If the task description or deadline is missing.
     */
    private String handleDeadline(String[] inputParts) throws InvalidMessageException {
        assert inputParts.length == 2 : "Input command should have 2 parts";

        try {
            String[] deadlineParts = inputParts[1].split("/by");
            Task task = new DeadlineTask(deadlineParts[0].trim(), deadlineParts[1].trim());

            return addTask(task);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidMessageException("Sorry, deadline needs a description and /by. :(");
        }
    }

    /**
     * Handles the 'event' command to add a new event task.
     *
     * @param inputParts The split input containing the command and command body.
     * @return A confirmation message with the added task details.
     * @throws InvalidMessageException If the task description, start time, or end time is missing.
     */
    private String handleEvent(String[] inputParts) throws InvalidMessageException {
        assert inputParts.length == 2 : "Input command should have 2 parts";

        try {
            String[] eventParts = inputParts[1].split("/from|/to");
            Task task = new EventTask(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim());

            return addTask(task);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidMessageException("Sorry, event needs a description, /from and /to. :(");
        }
    }

    /**
     * Adds a task to the storage.
     *
     * @param task The task to be added.
     * @return A confirmation message with the added task details and current task count.
     */
    private String addTask(Task task) {
        assert task != null : "Task cannot be null";

        storage.addTask(task);
        return String.format("Got it. Task saved:\n%s\n%d tasks in the list.", task, storage.getSize());
    }


    /**
     * Handles the 'delete' command to remove a task.
     *
     * @param inputParts The split input containing the command and task index.
     * @return A confirmation message with the deleted task details and current task count.
     * @throws InvalidMessageException If the task index is invalid.
     */
    private String handleDelete(String[] inputParts) throws InvalidMessageException {
        assert inputParts.length == 2 : "Input command should have 2 parts";

        try {
            int taskIdx = Integer.parseInt(inputParts[1]) - 1;
            Task task = storage.getTask(taskIdx);
            storage.deleteTask(taskIdx);

            return String.format("Sure. Task deleted:\n%s\n%d tasks in the list.", task, storage.getSize());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidMessageException("Sorry, delete needs a numerical task index. :(");
        }
    }

    /**
     * Handles the 'find' command to remove a task.
     *
     * @param inputParts The split input containing the command and command body.
     * @return A string representation of all the tasks that include the keywords.
     * @throws InvalidMessageException If keyword(s) is/are missing.
     */
    private String handleFind(String[] inputParts) throws InvalidMessageException {
        assert inputParts.length == 2 : "Input command should have 2 parts";

        try {
            String keyword = inputParts[1];
            List<Task> matchingTasks = storage.findTasks(keyword);

            if (matchingTasks.isEmpty()) {
                return "No matching tasks found.";
            }

            StringBuilder stringBuilder = new StringBuilder("Here are the matching tasks:\n");
            int index = 1;
            for (Task matchingTask : matchingTasks) {
                stringBuilder.append(index).append(". ").append(matchingTask.toString()).append("\n");
                index++;
            }
            return stringBuilder.toString().trim();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidMessageException("Sorry, please give keyword(s) to search for. :(");
        }
    }

}
