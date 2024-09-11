package donna;

import java.util.List;
import java.util.Scanner;

import donna.parse.ParsedCommand;
import donna.parse.Parser;
import donna.task.Deadline;
import donna.task.Event;
import donna.task.Task;
import donna.task.TaskList;
import donna.task.ToDo;


/**
 * Represents the main class for the Donna chatbot.
 */
public class Donna {
    private static final String DIRECTORY_PATH = "data";
    private static final String FILE_PATH = DIRECTORY_PATH + "/donna-tasks.txt";
    private static Storage storage;
    private Ui ui;
    private TaskList tasks;
    private final Parser parser;


    /**
     * Constructs a Donna instance, initializing the user interface, storage,
     * parser, and loading previously saved tasks.
     */
    public Donna() {
        storage = new Storage(DIRECTORY_PATH, FILE_PATH);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.loadTasks());
            ui = new Ui(!tasks.isEmpty());
        } catch (DonnaException e) {
            ui = new Ui(false);
            ui.display(ui.getErrorMessage(e.getMessage()));
            tasks = new TaskList();
        }
    }

    public Ui getDonnaUi() {
        return this.ui;
    }

    /**
     * Runs the main loop, processing user input and executing commands.
     * Handles exceptions and prints error messages as needed.
     */
    private void run() {
        ui.display(ui.getGreeting());

        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            try {
                ParsedCommand result = parser.parse(input);
                String commandType = result.getCommandType();

                switch (commandType) {
                case "exit":
                    ui.display(ui.getGoodbyeMessage());
                    storage.saveTasks(tasks);
                    sc.close();
                    return;
                case "list":
                    ui.display(ui.getTaskList(tasks));
                    break;
                case "mark":
                    ui.display(handleMark(result.getArgument1()));
                    break;
                case "unmark":
                    ui.display(handleUnmark(result.getArgument1()));
                    break;
                case "delete":
                    ui.display(handleDelete(result.getArgument1()));
                    break;
                case "add":
                    ui.display(handleAdd(result.getArgument1(), result.getArgument2()));
                    break;
                case "find":
                    List<Task> foundTasks = tasks.searchTasks(result.getArgument1());
                    ui.display(ui.findResults(foundTasks));
                    break;
                default:
                    throw DonnaException.invalidTaskType(commandType);
                }

            } catch (DonnaException e) {
                ui.display(ui.getErrorMessage(e.getMessage()));
            }
        }
    }

    /**
     * Handles the marking of a task as done.
     *
     * @param taskNum S.No of the task to mark (index from 1).
     * @return Donna's response confirming the marking of a task as done.
     * @throws DonnaException If the argument is not a valid task number.
     */
    private String handleMark(String taskNum) throws DonnaException {
        try {
            int taskIdx = Integer.parseInt(taskNum) - 1;
            Task task = tasks.markTask(taskIdx, true);
            storage.saveTasks(tasks);
            return ui.getTaskMarkedMessage(task, true);
        } catch (NumberFormatException e) {
            return ui.getErrorMessage(DonnaException.invalidTaskNumber().getMessage());
        }
    }

    /**
     * Handles the marking of a task as not done.
     *
     * @param taskNum Index of the task to unmark (index from 1).
     * @return Donna's response confirming the marking of a task as not done.
     * @throws DonnaException If the argument is not a valid task number.
     */
    private String handleUnmark(String taskNum) throws DonnaException {
        try {
            int taskIdx = Integer.parseInt(taskNum) - 1;
            Task task = tasks.markTask(taskIdx, false);
            storage.saveTasks(tasks);
            return ui.getTaskMarkedMessage(task, false);
        } catch (NumberFormatException e) {
            return ui.getErrorMessage(DonnaException.invalidTaskNumber().getMessage());
        }
    }

    /**
     * Handles the deletion of a task.
     *
     * @param taskNum Index of the task to delete (index from 1).
     * @return Donna's response confirming the deletion of a task.
     * @throws DonnaException If the argument is not a valid task number.
     */
    private String handleDelete(String taskNum) throws DonnaException {
        try {
            int taskIndex = Integer.parseInt(taskNum) - 1;
            Task task = tasks.deleteTask(taskIndex);
            storage.saveTasks(tasks);
            return ui.getTaskDeletedMessage(task, tasks.getTaskCount());
        } catch (NumberFormatException e) {
            return ui.getErrorMessage(DonnaException.invalidTaskNumber().getMessage());
        }
    }

    /**
     * Handles the addition of a task to the list.
     *
     * @param type Type of the task (e.g., "todo", "deadline", "event").
     * @param description Description of the task.
     * @return Donna's response confirming the addition of a task.
     * @throws DonnaException If the type or description is invalid.
     */
    private String handleAdd(String type, String description) throws DonnaException {
        Task newTask;

        switch (type) {
        case "todo":
            newTask = new ToDo(description);
            break;
        case "deadline":
            String[] deadlineParts = description.split(" /by ", 2);
            if (deadlineParts.length != 2) {
                throw DonnaException.emptyDescription(type);
            }
            newTask = new Deadline(deadlineParts[0], deadlineParts[1]);
            break;
        case "event":
            String[] eventParts = description.split(" /from ", 2);
            if (eventParts.length != 2) {
                throw DonnaException.emptyDescription(type);
            }
            String[] eventTimes = eventParts[1].split(" /to ", 2);
            if (eventTimes.length != 2) {
                throw DonnaException.emptyEventTime();
            }
            newTask = new Event(eventParts[0], eventTimes[0], eventTimes[1]);
            break;
        default:
            throw DonnaException.invalidTaskType(type);
        }
        tasks.addTask(newTask);
        storage.saveTasks(tasks);
        return ui.getTaskAddedMessage(newTask, tasks.getTaskCount());
    }

    /**
     * Returns Donna's response based on the input.
     *
     * @return Donna's response
     */
    public String getResponse(String input) {
        try {

            ParsedCommand result = parser.parse(input);
            String commandType = result.getCommandType();
            String response;

            switch (commandType) {
            case "exit":
                response = ui.getGoodbyeMessage();
                storage.saveTasks(tasks);
                return response;
            case "list":
                return ui.getTaskList(tasks);
            case "mark":
                return handleMark(result.getArgument1());
            case "unmark":
                return handleUnmark(result.getArgument1());
            case "delete":
                return handleDelete(result.getArgument1());
            case "add":
                return handleAdd(result.getArgument1(), result.getArgument2());
            case "find":
                List<Task> foundTasks = tasks.searchTasks(result.getArgument1());
                return ui.findResults(foundTasks);
            default:
                return input;
            }
        } catch (DonnaException e) {
            return ui.getErrorMessage(e.getMessage());
        }
    }

    /**
     * Main method to start the Donna application.
     *
     * @param args Arguments.
     */
    public static void main(String[] args) {
        new Donna().run();
    }
}
