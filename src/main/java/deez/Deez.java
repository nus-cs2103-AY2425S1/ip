package deez;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;

import deez.tasks.Deadline;
import deez.tasks.Event;
import deez.tasks.Task;
import deez.tasks.Todo;
import javafx.util.Pair;


/**
 * Deez class
 */
public class Deez {
    private static final DateTimeFormatter dateTimeInputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    private static Storage storage = new Storage("./data");
    protected TaskList taskList = new TaskList(new ArrayList<>());
    protected boolean isActive = true;
    private Ui ui = new Ui();

    /**
     * Constructor
     */
    public Deez() {
        if (storage.canLoad()) {
            try {
                taskList = storage.loadTasks();
            } catch (Exception e) {
                System.out.println("Failed to load save-file, it might possibly be corrupted!");
            }
        }
    }

    /**
     * Constructor for testing purposes
     *
     * @param isTesting flag to tell the constructor to not initialise taskList with storage.
     *                  This is important to preserve the correctness of DeezTest.
     */
    public Deez(Boolean isTesting) {
    }

    /**
     * Initialises the UI and sets the message consumer
     *
     * @param messageConsumer The function that will handle messages displayed by the UI
     */
    public void initialiseUi(Consumer<String> messageConsumer) {
        ui = new Ui(messageConsumer);
        ui.say("Hello! I'm Deez!", "What can I do you for?");
    }

    /**
     * Parse an integer from a given string
     *
     * @param s the input string
     * @return the parsed integer or throws DeezException if invalid
     */
    static int parseInt(String s) throws DeezException {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            throw new DeezException("Invalid input!", "Please enter a valid number.");
        }
    }

    /**
     * Handle the exit command
     */
    private void handleExit() {
        // TODO: handle exit
    }

    /**
     * List all tasks
     */
    private void handleListTasks() {
        ui.printList(taskList.getTasks());
    }

    /**
     * Handle an invalid command
     *
     * @throws DeezException
     */
    private void handleInvalidCommand() throws DeezException {
        throw new DeezException("Please enter a valid command.");
    }

    /**
     * Add a todo task
     *
     * @param props the properties to use for adding the task
     * @throws DeezException if any issue occurs during addition
     */
    private void handleAddTodo(Properties props) throws DeezException {
        String name = props.getProperty("name");
        if (name.isBlank()) {
            throw new DeezException("Please provide a description for the todo.");
        }
        Todo t = new Todo(props.getProperty("name"));
        taskList.addTask(t);
        ui.say("Easy. I have added your task.", t.toString(),
            "You have " + taskList.size() + " tasks in the " + "list");
    }

    /**
     * Add a deadline task
     *
     * @param props the properties to use for adding the task
     * @throws DeezException if any issue occurs during addition
     */
    private void handleAddDeadline(Properties props) throws DeezException {
        try {
            LocalDateTime byDateTime = LocalDateTime.parse(props.getProperty("by"), dateTimeInputFormatter);
            Deadline d = new Deadline(props.getProperty("name"), byDateTime);
            taskList.addTask(d);
            ui.say("Donezo. I have added your task.", d.toString(),
                "You have " + taskList.size() + " tasks in the " + "list");
        } catch (DateTimeParseException e) {
            throw new DeezException("Failed to parse deadline date.", "Usage:",
                "deadline " + "return book" + " /by 2019-10-15 1800");
        }
    }

    /**
     * Add an event task
     *
     * @param props the properties to use for adding the task
     * @throws DeezException if any issue occurs during addition
     */
    private void handleAddEvent(Properties props) throws DeezException {
        try {
            LocalDateTime startDate = LocalDateTime.parse(props.getProperty("from"), dateTimeInputFormatter);
            LocalDateTime endDate = LocalDateTime.parse(props.getProperty("to"), dateTimeInputFormatter);
            if (startDate.isAfter(endDate)) {
                throw new DeezException("Start date must be before end date.", "Usage:",
                    "event project meeting /from 2019-10-15 1800 /to 2019-10-15 1900");
            }
            Event e = new Event(props.getProperty("name"), startDate, endDate);
            taskList.addTask(e);
            ui.say("Event added", e.toString(), "You have " + taskList.size() + " tasks in the " + "list");
        } catch (DateTimeParseException e) {
            throw new DeezException("Invalid date.", "Usage:",
                "event " + "project meeting /from 2019-10-15 1800 /to 2019-10-15 1900");
        }
    }

    /**
     * Mark or unmark a task as done
     *
     * @param isMarkDone whether to mark or unmark the task
     * @param props      the properties to use for marking/unmarking
     * @throws DeezException if any issue occurs during marking/unmarking
     */
    private void handleMarkUnmarkDone(boolean isMarkDone, Properties props) throws DeezException {
        int taskIdx = parseInt(props.getProperty("index"));
        try {
            Task t = taskList.get(taskIdx - 1);
            if (isMarkDone && !t.isDone()) {
                t.toggleDone();
            } else if (!isMarkDone && t.isDone()) {
                t.toggleDone();
            }
            ui.say("Updated task:", t.toString());
        } catch (Exception e) {
            throw new DeezException("No task at index " + taskIdx, "Please try again.");
        }
    }

    private void handleDeleteTask(Properties props) throws DeezException {
        int taskIdx = parseInt(props.getProperty("index"));
        try {
            Task t = taskList.get(taskIdx - 1);
            taskList.remove(t);
            ui.say("Deleted task:", t.toString(), taskList.size() + " tasks remain.");
        } catch (Exception e) {
            throw new DeezException("No task at index " + taskIdx, "Please try again.");
        }
    }

    private void handleFind(Properties props) throws DeezException {
        String keyword = props.getProperty("keyword");
        if (keyword == null || keyword.isEmpty()) {
            throw new DeezException("No keyword provided.", "Usage:", "find book", "Please try again.");
        }
        List<Task> foundTasks = taskList.getTasks(keyword);
        ui.printList(foundTasks);
    }

    private void handleSave() {
        storage.saveTasks(taskList);
        ui.say("Saved successfully!");
    }

    /**
     * Handle a command
     *
     * @param inputPair the pair of command and properties to handle
     */
    protected void handleCommand(Pair<Command, Properties> inputPair) {
        Command cmd = inputPair.getKey();
        Properties props = inputPair.getValue();
        switch (cmd) {
        case EXIT -> handleExit();
        case LIST -> handleListTasks();
        case TODO -> handleAddTodo(props);
        case DEADLINE -> handleAddDeadline(props);
        case EVENT -> handleAddEvent(props);
        case MARK -> handleMarkUnmarkDone(true, props);
        case UNMARK -> handleMarkUnmarkDone(false, props);
        case DELETE -> handleDeleteTask(props);
        case SAVE -> handleSave();
        case FIND -> handleFind(props);
        case UNKNOWN -> handleInvalidCommand();
        default -> handleInvalidCommand();
        }
    }

    /**
     * Handle input from the user
     *
     * @param inputStr the string to be parsed and handled
     */
    public void handleInput(String inputStr) {
        try {
            Pair<Command, Properties> inputPair = Parser.parse(inputStr);
            handleCommand(inputPair);
        } catch (DeezException e) {
            ui.say(e.getErrorMessages());
        }
    }
}
