package babblebot;

import java.io.IOException;

import babblebot.exception.BabbleBotException;
import babblebot.parser.Parser;
import babblebot.storage.Storage;
import babblebot.task.Deadline;
import babblebot.task.Event;
import babblebot.task.Task;
import babblebot.task.TaskList;
import babblebot.task.Todo;
import babblebot.ui.Ui;


/**
 * The BabbleBot class represents the main application that manages tasks.
 * It allows adding, deleting, and retrieving tasks through a TaskList.
 */
public class BabbleBot {
    private static final String TASK_LIST_PATH = "./data/babblebot.txt";
    private static TaskList storedTasks;
    private Ui ui;
    private Storage storage;
    private Parser parser;
    private boolean isFirstInteraction = true;

    /**
     * Constructs a new BabbleBot instance and initializes the components.
     * Loads tasks from the specified file path.
     *
     * @param filePath The file path to load the task list from.
     */
    public BabbleBot(String filePath) {
        assert filePath != null : "File path cannot be null";
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            storedTasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showIoError();
            storedTasks = new TaskList();
        }
    }

    public BabbleBot() {
        this(TASK_LIST_PATH);
    }

    /**
     * Generates a response String for the user's chat message.
     *
     * @param input The user's input string
     */
    public String getResponse(String input) {
        String command = parser.parseCommand(input);

        try {
            switch (command) {
            case "bye":
                return ui.getGoodbyeMessage();

            case "list":
                return ui.getTaskListString(storedTasks);

            case "todo":
                return handleTodoCommand(input);

            case "deadline":
                return handleDeadlineCommand(input);

            case "event":
                return handleEventCommand(input);

            case "delete":
                return handleDeleteCommand(input);

            case "mark":
                return handleMarkCommand(input);

            case "unmark":
                return handleUnmarkCommand(input);

            case "find":
                return handleFindCommand(input);

            default:
                throw new BabbleBotException();
            }
        } catch (BabbleBotException e) {
            return ui.getBabbleBotErrorString();
        }
    }

    /**
     * Handles the tasklist manipulation for finding task with a matching keyword
     *
     * @param input The user's input string, which is the keyword
     * @return The message resulting from finding a task with a matching keyword
     */
    private String handleFindCommand(String input) {
        try {
            String keyword = parser.parseKeyword(input);
            TaskList matchingTasks = new TaskList(storedTasks.getMatchingTasks(keyword));
            return ui.getTaskListString(matchingTasks);
        } catch (IndexOutOfBoundsException e) {
            return ui.getIoErrorString() + "\nPlease provide a valid keyword to search for.";
        }
    }

    /**
     * Handles the tasklist manipulation for unmarking a task
     *
     * @param input The user's input string
     * @return The message resulting from unmarking a task
     */
    private String handleUnmarkCommand(String input) {
        try {
            int unmarkIndex = parser.parseIndex(input);
            storedTasks.get(unmarkIndex).markAsUndone();
            saveTasksToFile();
            return ui.getUnmarkMessageString(storedTasks, unmarkIndex);
        } catch (IndexOutOfBoundsException e) {
            return ui.getIoErrorString() + "\nPlease provide a valid task number to unmark.";
        }
    }

    /**
     * Handles the tasklist manipulation for marking a task
     *
     * @param input The user's input string
     * @return The message resulting from marking a task
     */
    private String handleMarkCommand(String input) {
        try {
            int markIndex = parser.parseIndex(input);
            storedTasks.get(markIndex).markAsDone();
            saveTasksToFile();
            return ui.getMarkMessageString(storedTasks, markIndex);
        } catch (IndexOutOfBoundsException e) {
            return ui.getIoErrorString() + "\nPlease provide a valid task number to mark as done.";
        }
    }

    /**
     * Handles the tasklist manipulation for deleting a task
     *
     * @param input The user's input string
     * @return The message resulting from deleting a task
     */
    private String handleDeleteCommand(String input) {
        try {
            int index = parser.parseIndex(input);
            assert index >= 0 && index < storedTasks.size() : "Invalid task index";
            Task taskToDelete = storedTasks.get(index);
            String returnMessage = ui.getRemoveMessageString(storedTasks, index);
            storedTasks.deleteTask(index);
            assert !storedTasks.contains(taskToDelete) : "Task was not deleted";
            saveTasksToFile();
            return returnMessage;
        } catch (IndexOutOfBoundsException e) {
            return ui.getIoErrorString() + "\nOops! Please provide a valid task number to delete.";
        }
    }

    /**
     * Handles the tasklist manipulation for adding a Event task
     *
     * @param input The user's input string
     * @return The message resulting from adding the Event task
     */
    private String handleEventCommand(String input) {
        try {
            String[] parsedEvent = parser.parseEventContent(input);
            storedTasks.addTask(new Event(parsedEvent[0], parsedEvent[1], parsedEvent[2]));
            assert storedTasks.size() == storedTasks.size() + 1 : "Task was not added correctly";
            saveTasksToFile();
            return ui.getTaskAddedString(storedTasks);
        } catch (IndexOutOfBoundsException e) {
            return ui.getIoErrorString() + "\nPlease provide a valid event.";
        }
    }

    /**
     * Handles the tasklist manipulation for adding a Deadline task
     *
     * @param input The user's input string
     * @return The message resulting from adding the Deadline task
     */
    private String handleDeadlineCommand(String input) {
        try {
            String[] parsedDeadline = parser.parseDeadlineContent(input);
            storedTasks.addTask(new Deadline(parsedDeadline[0], parsedDeadline[1]));
            assert storedTasks.size() == storedTasks.size() + 1 : "Task was not added correctly";
            saveTasksToFile();
            return ui.getTaskAddedString(storedTasks);
        } catch (IndexOutOfBoundsException e) {
            return ui.getIoErrorString() + "\nPlease provide a valid deadline.";
        }
    }

    /**
     * Handles the tasklist manipulation for adding a Todo task
     *
     * @param input The user's input string
     * @return The message resulting from adding the todo task
     */
    private String handleTodoCommand(String input) {
        try {
            String content = parser.parseTodoContent(input);
            storedTasks.addTask(new Todo(content));
            assert storedTasks.size() == storedTasks.size() + 1 : "Task was not added correctly";
            saveTasksToFile();
            return ui.getTaskAddedString(storedTasks);
        } catch (IndexOutOfBoundsException e) {
            return ui.getTodoErrorString();
        }
    }

    /**
     * Saves the current tasks to the file specified in the Storage component.
     */
    private void saveTasksToFile() {
        try {
            int initialSize = storedTasks.size();
            storage.save(storedTasks);
            TaskList loadedTasks = new TaskList(storage.load());
            assert loadedTasks.size() == initialSize : "Mismatch between saved and loaded tasks";
        } catch (IOException e) {
            ui.showIoError();
        }
    }
}


