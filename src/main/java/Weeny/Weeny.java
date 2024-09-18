package weeny;


import weeny.ui.Ui;
import weeny.task.Todo;
import weeny.task.Event;
import weeny.task.Deadline;
import weeny.task.TaskList;
import weeny.task.Task;
import weeny.storage.Storage;
import weeny.parser.Parser;
import weeny.ui.WeenyGui;

import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Main class for the Weeny task management application.
 * Manages user commands and handles tasks.
 */
public class Weeny extends Application {
    public static final int TODO_LENGTH = 5;
    public static final int DEADLINE_LENGTH = 9;
    public static final int EVENT_LENGTH = 6;
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;
    private final Parser parser;

    /**
     * Constructs a Weeny application instance.
     * Initializes UI, storage, parser, and task list.
     * Ensures data directory and TaskList.txt file exist and loads existing tasks.
     */
    public Weeny() {
        ui = new Ui(); // UI for user interactions
        storage = new Storage(); // Manages task data storage
        parser = new Parser(); // Parses user input
        taskList = new TaskList(); // List of tasks
        try {
            // Ensure data directory and TaskList.txt file exist
            storage.createFileIfNotExist("./data", "TaskList.txt");

            // Load tasks from file
            taskList.getTasks().addAll(storage.loadTask("./data/TaskList.txt"));
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Call start method in WeenyGui
     * Overrides start method in Application to start GUI
     *
     *
     * @param stage Parsed by javafx.Application
     */
    @Override
    public void start(Stage stage) {
        WeenyGui weenyGui = new WeenyGui();
        weenyGui.start(stage);
    }


    /**
     * Starts the Weeny application.
     * Initializes UI, storage, parser, and task list. Processes user commands.
     * Handles commands like "list", "bye", "mark", "unmark", "todo", "event", "deadline", "delete", "find", and "schedule".
     * Saves tasks to the file when exiting.
     *
     * @param input User input into textbox
     * @return Returns string from calls to different scenarios
     */
    public String executeWeeny(String input) {
        boolean isProgramEnd = false;
        int initialSize = 0;
        while (!isProgramEnd) {
            String command = parser.extractFirstWord(input);

            try {
                switch (command) {
                case "list":
                    return ui.showTaskList(taskList.getTasks());

                case "bye":
                    isProgramEnd = true;
                    storage.saveTask("/data/TaskList.txt", taskList.getTasks());
                    break;

                case "mark":
                    validateMarkInput(input);
                    int markIndex = parser.extractEndNumber(input) - 1;
                    validateIndex(markIndex, taskList.size(), "mark");
                    taskList.markAsDone(markIndex);
                    return ui.showMarkMessage(taskList.getTask(markIndex));

                case "unmark":
                    validateUnmarkDeleteInput(input);
                    int unmarkIndex = parser.extractEndNumber(input) - 1;
                    validateIndex(unmarkIndex, taskList.size(), "unmark");
                    taskList.markAsNotDone(unmarkIndex);
                    return ui.showUnmarkMessage(taskList.getTask(unmarkIndex));

                case "todo":
                    initialSize = taskList.size();
                    validateTodoInput(input);
                    Task todoTask = new Todo(input.substring(5).trim());
                    taskList.addTask(todoTask);
                    return ui.showTaskAddedMessage(todoTask, taskList.size());

                case "event":
                    initialSize = taskList.size();
                    validateEventInput(input);
                    Task eventTask = new Event(parser.extractEventName(input),
                            parser.extractEventTimes(input)[0],
                            parser.extractEventTimes(input)[1]);
                    taskList.addTask(eventTask);
                    return ui.showTaskAddedMessage(eventTask, taskList.size());

                case "deadline":
                    initialSize = taskList.size();
                    validateDeadlineInput(input);
                    Task deadlineTask = new Deadline(parser.extractDeadlineName(input),
                            parser.extractDeadlineTime(input));
                    taskList.addTask(deadlineTask);
                    return ui.showTaskAddedMessage(deadlineTask, taskList.size());

                case "delete":
                    validateUnmarkDeleteInput(input);
                    int initialDeleteSize = taskList.size();
                    int deleteIndex = parser.extractEndNumber(input) - 1;
                    validateIndex(deleteIndex, taskList.size(), "delete");
                    Task removedTask = taskList.getTask(deleteIndex);
                    taskList.deleteTask(deleteIndex);
                    return ui.showTaskDeletedMessage(removedTask, taskList.size());

                case "find":
                    validateFindScheduleInput(input, "find");
                    String keyWord = input.substring(5);
                    return ui.showSearchResult(taskList.findTask(keyWord));

                case "schedule":
                    validateFindScheduleInput(input, "schedule");
                    String date = input.substring(9);
                    return ui.showScheduleMessage(taskList.getSchedule(date), date);

                default:
                    throw new UnsupportedOperationException("Uh Oh I don't quite understand that. " +
                            "Here are the list of commands:\n" +
                            "1. todo <task name>\n" +
                            "2. deadline <task name> /by dd/MM/yyyy HHmm\n" +
                            "3. event <task name> /from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm\n" +
                            "4. list\n" +
                            "5. delete <index>\n" +
                            "6. mark <index>\n" +
                            "7. unmark <index>\n" +
                            "8. find <keyword>\n" +
                            "9. schedule dd/MM/yyyy\n");
                }
            } catch (IllegalArgumentException | IndexOutOfBoundsException | UnsupportedOperationException e) {
                return ui.showError(e.getMessage());
            }
        }
        storage.saveTask("./data/TaskList.txt", taskList.getTasks());
        return ui.showGoodbyeMessage();
    }

    /**
     * Checks if the index is within the task list range.
     *
     * @param index The index to check.
     * @param size The size of the task list.
     * @param action The action being performed (e.g., "mark", "delete").
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    protected static void validateIndex(int index, int size, String action) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Invalid index for " + action + " action.");
        }
    }

    /**
     * Validates input for a "todo" command.
     *
     * @param input The command input.
     * @throws IllegalArgumentException if the input is too short.
     */
    private static void validateTodoInput(String input) {
        if (input.length() <= TODO_LENGTH) {
            throw new IllegalArgumentException("To-Do description is too short.");
        }
    }

    /**
     * Validates input for an "event" command.
     *
     * @param input The command input.
     * @throws IllegalArgumentException if the input is missing details like "/from" or "/to".
     */
    private static void validateEventInput(String input) {
        if (input.length() <= EVENT_LENGTH || !input.contains("/from") || !input.contains("/to")) {
            throw new IllegalArgumentException("Event details are incomplete.");
        }
    }

    /**
     * Validates input for a "deadline" command.
     *
     * @param input The command input.
     * @throws IllegalArgumentException if the input is missing the "/by" date.
     * @throws IllegalArgumentException if the input is missing the "/by" date.
     */
    private static void validateDeadlineInput(String input) {
        if (input.length() <= DEADLINE_LENGTH || !input.contains("/by")) {
            throw new IllegalArgumentException("Deadline details are incomplete.");
        }
    }

    /**
     * Validates input for a "find" and "schedule" command.
     *
     * @param input The command input.
     * @throws IllegalArgumentException if the input is missing the required keyword or date.
     */
    private void validateFindScheduleInput(String input, String command) {
        if (input.length() <= (command.equals("find") ? 5 : 9)) {
            throw new IllegalArgumentException((command.equals("find") ? "Search keyword" : "Date") + " is missing.");
        }
    }

    /**
     * Validates input for a "mark" command.
     *
     * @param input The command input.
     * @throws IllegalArgumentException if the index is missing or invalid.
     */
    private void validateMarkInput(String input) {
        if (input.length() <= 5 || !input.substring(5).matches("\\d+")) {
            throw new IllegalArgumentException("Index is missing or invalid.");
        }
    }

    /**
     * Validates input for a "unmark" and "delete" command.
     *
     * @param input The command input.
     * @throws IllegalArgumentException if the index is missing or invalid.
     */
    private void validateUnmarkDeleteInput(String input) {
        if (input.length() <= 7 || !input.substring(7).matches("\\d+")) {
            throw new IllegalArgumentException("Index is missing or invalid.");
        }
    }

}
