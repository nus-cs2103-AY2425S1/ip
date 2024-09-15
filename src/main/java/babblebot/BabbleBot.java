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
                try {
                    String content = parser.parseTodoContent(input);
                    storedTasks.addTask(new Todo(content));
                    assert storedTasks.size() == storedTasks.size() + 1 : "Task was not added correctly";
                    saveTasksToFile();
                    return ui.getTaskAddedString(storedTasks);
                } catch (IndexOutOfBoundsException e) {
                    return ui.getTodoErrorString();
                }

            case "deadline":
                try {
                    String[] parsedDeadline = parser.parseDeadlineContent(input);
                    storedTasks.addTask(new Deadline(parsedDeadline[0], parsedDeadline[1]));
                    assert storedTasks.size() == storedTasks.size() + 1 : "Task was not added correctly";
                    saveTasksToFile();
                    return ui.getTaskAddedString(storedTasks);
                } catch (IndexOutOfBoundsException e) {
                    return ui.getIoErrorString() + "\nPlease provide a valid deadline.";
                }

            case "event":
                try {
                    String[] parsedEvent = parser.parseEventContent(input);
                    storedTasks.addTask(new Event(parsedEvent[0], parsedEvent[1], parsedEvent[2]));
                    assert storedTasks.size() == storedTasks.size() + 1 : "Task was not added correctly";
                    saveTasksToFile();
                    return ui.getTaskAddedString(storedTasks);
                } catch (IndexOutOfBoundsException e) {
                    return ui.getIoErrorString() + "\nPlease provide a valid event.";
                }

            case "delete":
                try {
                    int index = parser.parseIndex(input);
                    assert index >= 0 && index < storedTasks.size() : "Invalid task index";
                    Task taskToDelete = storedTasks.get(index);
                    storedTasks.deleteTask(index);
                    assert !storedTasks.contains(taskToDelete) : "Task was not deleted";
                    saveTasksToFile();
                    return ui.getRemoveMessageString(storedTasks, index);
                } catch (IndexOutOfBoundsException e) {
                    return ui.getIoErrorString() + "\nOops! Please provide a valid task number to delete.";
                }

            case "mark":
                try {
                    int markIndex = parser.parseIndex(input);
                    storedTasks.get(markIndex).markAsDone();
                    saveTasksToFile();
                    return ui.getMarkMessageString(storedTasks, markIndex);
                } catch (IndexOutOfBoundsException e) {
                    return ui.getIoErrorString() + "\nPlease provide a valid task number to mark as done.";
                }

            case "unmark":
                try {
                    int unmarkIndex = parser.parseIndex(input);
                    storedTasks.get(unmarkIndex).markAsUndone();
                    saveTasksToFile();
                    return ui.getUnmarkMessageString(storedTasks, unmarkIndex);
                } catch (IndexOutOfBoundsException e) {
                    return ui.getIoErrorString() + "\nPlease provide a valid task number to unmark.";
                }

            case "find":
                try {
                    String keyword = parser.parseKeyword(input);
                    TaskList matchingTasks = new TaskList(storedTasks.getMatchingTasks(keyword));
                    return ui.getTaskListString(matchingTasks);
                } catch (IndexOutOfBoundsException e) {
                    return ui.getIoErrorString() + "\nPlease provide a valid keyword to search for.";
                }

            default:
                throw new BabbleBotException();
            }
        } catch (BabbleBotException e) {
            return ui.getBabbleBotErrorString();
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
                        ui.showIoError();
                    }
                    break;

                case "event":
                    try {
                        String[] parsedEvent = parser.parseEventContent(userInp);
                        storedTasks.addTask(new Event(parsedEvent[0], parsedEvent[1], parsedEvent[2]));
                        ui.showTaskAdded(storedTasks);
                        saveTasksToFile();
                    } catch (IndexOutOfBoundsException e) {
                        ui.showIoError();
                    }
                    break;

                case "delete":
                    try {
                        int index = parser.parseIndex(userInp);
                        ui.showRemoveMessage(storedTasks, index);
                        saveTasksToFile();
                    } catch (IndexOutOfBoundsException e) {
                        ui.showIoError();
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
                        ui.showIoError();
                    }
                    break;

                case "find":
                    try {
                        String keyword = parser.parseKeyword(userInp);
                        TaskList matchingTasks = new TaskList(storedTasks.getMatchingTasks(keyword));
                        ui.showTaskList(matchingTasks);
                    } catch (IndexOutOfBoundsException e) {
                        ui.showIoError();
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


