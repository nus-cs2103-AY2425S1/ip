package neko;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import neko.task.*;
import neko.ui.Ui;

/**
 * The Neko class represents a cute cat chatbot that serves as a task manager.
 *  It handles user input and executes commands related to task management, such as adding,
 *  deleting, marking tasks as done, and listing all tasks. The tasks are stored in a file,
 *  which is loaded when the Neko object is instantiated.
 *
 * @author  Siow Rui Ming
 * @version 0.1
 * @since   2024-08-19
 */


public class Neko {
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_HELP = "help";
    private static final String FILE_PATH = "./data/neko.txt";
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    /**
     * Constructs a new Neko object with the specified file path to store the tasks.
     * Initializes the UI, storage, and task list. If the tasks file is not found, it initializes
     * an empty task list.
     *
     * @param filePath The path of the file to store tasks.
     */

    public Neko(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException e) {
            ui.showMessage(e.getMessage());
            tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * The main method which makes use of the run method to start the chatbot.
     *
     * @param args Unused.
     */

    public static void main(String[] args) {
        Neko neko = new Neko(FILE_PATH);
        neko.run();
    }

    /**
     * Runs the Neko chatbot, displaying a greeting, processing user commands,
     * and continuing until the exit command is received.
     * Handles user input by calling handleInput method.
     */

    public void run() {

        ui.showGreeting();

        String input = ui.getUserCommand();
        while (!input.equals(COMMAND_EXIT)) {
            try {
                ui.showMessage(handleInput(input));
            } catch (NekoException | IOException e) {
                ui.showMessage(e.getMessage());
            } finally {
                input = ui.getUserCommand();
            }
        }
        ui.showExitMessage();
    }

    public String getGreeting() {
        return ui.getGreetingMessage();
    }

    /**
     * Processes the user input based on the parsed command.
     * Handles commands such as listing tasks, marking tasks as done,
     * unmarking tasks, deleting tasks, and adding tasks.
     *
     * @param input The full user input string.
     * @throws NekoException If an unknown command is provided or the task cannot be marked/unmarked.
     * @throws IOException If there is an issue with reading/writing to the tasks file.
     */

    public String handleInput(String input) throws NekoException, IOException {
        String command = Parser.parseCommand(input);
        Task task;
        Task[] tasksArray;
        String response;
        switch (command) {
        case COMMAND_LIST:
            response = tasks.listTasks();
            break;
        case COMMAND_MARK:
            Integer[] markIndices = Parser.parseIndices(input);
            tasksArray = tasks.markTask(markIndices);
            storage.rewriteFile(tasks);
            response = formatTaskArrayResponse(tasksArray, "marked as done");
            break;
        case COMMAND_UNMARK:
            Integer[] unmarkIndices = Parser.parseIndices(input);
            tasksArray = tasks.unmarkTask(unmarkIndices);
            storage.rewriteFile(tasks);
            response = formatTaskArrayResponse(tasksArray, "marked as not done");
            break;
        case COMMAND_DELETE:
            Integer[] deleteIndices = Parser.parseIndices(input);
            tasksArray = tasks.deleteTask(deleteIndices);
            storage.rewriteFile(tasks);
            response = formatTaskArrayResponse(tasksArray, "delete");
            break;
        case COMMAND_ADD:
            task = createTask(input);
            if (task == null) {
                response = ui.getInvalidInputMessage();
                break;
            }
            tasks.addTask(task);
            storage.writeFile(task);
            response = "Purrfect! I've added this task meow ฅ/ᐠᓀ ﻌ ᓂマ\n "
                    + task + "\nNow you have " + tasks.size() + " tasks in your list meow";
            break;
        case COMMAND_FIND:
            String key = input.split(" ")[1].trim();
            String tasksFound = tasks.findTasks(key);
            if (tasksFound.isEmpty()) {
                response = "No tasks found meow!";
            } else {
                response = "Here are the matching tasks in your list meow:\n" + tasksFound;
            }
            break;
        case COMMAND_HELP:
            response = getHelpMessage();
            break;
        default:
            response = ui.getInvalidInputMessage();
        }
        return response;
    }

    private Task createTask(String input) throws NekoException {
        String[] parsedInput = Parser.parseAddCommand(input);
        Task task;
        try {
            String taskType = parsedInput[1].trim();
            String taskName = parsedInput[2].trim();

            switch (taskType) {
            case "todo":
                if (parsedInput.length != 3) {
                    throw new NekoException("Wrong format meow!");
                }
                task = new Todo(taskName);
                break;
            case "deadline":
                if (parsedInput.length != 4) {
                    throw new NekoException("Wrong format meow!");
                }
                LocalDateTime by = Parser.parseTime(parsedInput[3].trim());
                task = new Deadline(taskName, by);
                break;
            case "event":
                if (parsedInput.length != 5) {
                    throw new NekoException("Wrong format meow!");
                }
                LocalDateTime start = Parser.parseTime(parsedInput[3].trim());
                LocalDateTime end = Parser.parseTime(parsedInput[4].trim());
                task = new Event(taskName, start, end);
                break;
            default:
                throw new NekoException("Invalid input meow");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NekoException("Invalid input meow");
        }
        return task;
    }

    private String getHelpMessage() {
        return "Here are the available commands meow!\n\n"
                + "1. list - Displays all the tasks in the list\n"
                + "2. add todo [description] - Adds a new Todo task\n"
                + "3. add deadline [description] /by [deadline] - Adds a new Deadline task\n"
                + "   (Use the format: yyyyMMddTHH:mm for deadlines)\n"
                + "4. add event [description] /from [start time] /to [end time] - Adds a new Event task\n"
                + "   (Use the format: yyyyMMddTHH:mm for start and end times)\n"
                + "5. mark [task number] - Marks the task at the given index as done\n"
                + "6. unmark [task number] - Marks the task at the given index as not done\n"
                + "7. delete [task number] - Deletes the task at the given index\n"
                + "8. find [keyword] - Finds tasks that match the given keyword\n"
                + "9. help - Shows this list of commands\n";
    }

    private String formatTaskArrayResponse(Task[] tasks, String action) {
        StringBuilder response = new StringBuilder("Nice meow! I've ");
        response.append(action).append(" the following tasks:\n");

        for (Task task : tasks) {
            if (task != null) {
                response.append(task).append("\n");
            }
        }

        return response.toString().trim();
    }
}
