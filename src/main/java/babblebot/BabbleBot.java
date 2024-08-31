package babblebot;

import babblebot.exception.BabbleBotException;
import babblebot.parser.Parser;
import babblebot.storage.Storage;
import babblebot.task.Deadline;
import babblebot.task.Event;
import babblebot.task.TaskList;
import babblebot.task.Todo;
import babblebot.ui.Ui;
import java.io.IOException;

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

    /**
     * Constructs a new BabbleBot instance and initializes the components.
     * Loads tasks from the specified file path.
     *
     * @param filePath The file path to load the task list from.
     */
    public BabbleBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            storedTasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showIOError();
            storedTasks = new TaskList();
        }
    }
    /**
     * Saves the current tasks to the file specified in the Storage component.
     */
    private void saveTasksToFile() {
        try {
            storage.save(storedTasks);
        } catch (IOException e) {
            ui.showIOError();
        }
    }
    /**
     * Runs the BabbleBot application, processing user commands
     * until the "bye" command is received.
     */
    public void run() {
        ui.sayWelcome();
        boolean notBye = true;

        while (notBye) {
            String userInp = ui.readCommand();
            String command = parser.parseCommand(userInp);

            try {
                switch (command) {
                case "bye":
                    notBye = false;
                    ui.sayGoodbye();
                    break;

                case "list":
                    ui.showTaskList(storedTasks);
                    break;

                case "todo":
                    try {
                        String content = parser.parseTodoContent(userInp);
                        storedTasks.addTask(new Todo(content));
                        ui.showTaskAdded(storedTasks);
                        saveTasksToFile();
                    } catch (IndexOutOfBoundsException e) {
                        ui.showTodoError();
                    }
                    break;

                case "deadline":
                    try {
                        String[] parsedDeadline = parser.parseDeadlineContent(userInp);
                        storedTasks.addTask(new Deadline(parsedDeadline[0], parsedDeadline[1]));
                        ui.showTaskAdded(storedTasks);
                        saveTasksToFile();
                    } catch (IndexOutOfBoundsException e) {
                        ui.showIOError();
                    }
                    break;

                case "event":
                    try {
                        String[] parsedEvent = parser.parseEventContent(userInp);
                        storedTasks.addTask(new Event(parsedEvent[0], parsedEvent[1], parsedEvent[2]));
                        ui.showTaskAdded(storedTasks);
                        saveTasksToFile();
                    } catch (IndexOutOfBoundsException e) {
                        ui.showIOError();
                    }
                    break;

                case "delete":
                    try {
                        int index = parser.parseIndex(userInp);
                        ui.showRemoveMessage(storedTasks, index);
                        saveTasksToFile();
                    } catch (IndexOutOfBoundsException e) {
                        ui.showIOError();
                    }
                    break;

                case "mark":
                    try {
                        int markIndex = parser.parseIndex(userInp);
                        storedTasks.get(markIndex).markAsDone();
                        ui.showMarkMessage(storedTasks, markIndex);
                        saveTasksToFile();
                    } catch (IndexOutOfBoundsException e) {
                        ui.showTodoError();
                    }
                    break;

                case "unmark":
                    try {
                        int unmarkIndex = parser.parseIndex(userInp);
                        storedTasks.get(unmarkIndex).markAsUndone();
                        ui.showUnmarkMessage(storedTasks, unmarkIndex);
                        saveTasksToFile();
                    } catch (IndexOutOfBoundsException e) {
                        ui.showIOError();
                    }
                    break;

                default:
                    throw new BabbleBotException();
                }
            } catch (BabbleBotException e) {
                ui.showBabbleBotError();
            }
        }
    }
    /**
     * The main entry point of the BabbleBot application.
     * Initializes and runs a new instance of BabbleBot.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new BabbleBot(TASK_LIST_PATH).run();
    }
}
