package neko;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import neko.task.Deadline;
import neko.task.Event;
import neko.task.Task;
import neko.task.TaskList;
import neko.task.Todo;
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
    private static final String COMMAND_VIEW = "view";
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

    /**
     * Get the greeting message from the Ui class.
     * @return greeting message as a string
     */

    public String getGreeting() {
        return ui.getGreetingMessage();
    }

    /**
     * Processes the user input based on the parsed command.
     * Handles commands such as listing tasks, marking tasks as done,
     * unmarking tasks, deleting tasks, and adding tasks.
     *
     * @param input The full user input string.
     * @return The response string to be displayed to the user.
     * @throws NekoException If an unknown command is provided or a task-related operation fails.
     * @throws IOException If there is an issue with reading/writing to the tasks file.
     */
    public String handleInput(String input) throws NekoException, IOException {
        String command = Parser.parseCommand(input);
        String response;
        switch (command) {
        case COMMAND_LIST:
            response = tasks.listTasks();
            break;
        case COMMAND_MARK:
            response = handleMarkCommand(input);
            break;
        case COMMAND_UNMARK:
            response = handleUnmarkCommand(input);
            break;
        case COMMAND_DELETE:
            response = handleDeleteCommand(input);
            break;
        case COMMAND_ADD:
            response = handleAddCommand(input);
            break;
        case COMMAND_FIND:
            response = handleFindCommand(input);
            break;
        case COMMAND_HELP:
            response = getHelpMessage();
            break;
        case COMMAND_VIEW:
            response = handleViewCommand(input);
            break;
        default:
            response = ui.getInvalidInputMessage();
        }
        return response;
    }

    /**
     * Creates a task (Todo, Deadline, or Event) based on the input string provided by the user.
     * The method first parses the input to determine the task type and description, and then
     * calls the respective creation method for each task type.
     *
     * @param input The user input string containing the task type and details.
     * @return The created task, either a Todo, Deadline, or Event, based on the user input.
     * @throws NekoException If the input format is invalid or the task type is not recognized.
     */
    private Task createTask(String input) throws NekoException {
        String[] parsedInput = Parser.parseAddCommand(input);
        Task task;
        try {
            String taskType = parsedInput[1].trim();
            String taskName = parsedInput[2].trim();

            switch (taskType) {
            case "todo":
                task = createTodoTask(parsedInput, taskName);
                break;
            case "deadline":
                task = createDeadlineTask(parsedInput, taskName);
                break;
            case "event":
                task = createEventTask(parsedInput, taskName);
                break;
            default:
                throw new NekoException("Invalid input meow");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NekoException("Invalid input meow");
        }
        return task;
    }

    // Private methods to handle individual task types (Todo, Deadline, Event)
    private Task createTodoTask(String[] parsedInput, String taskName) throws NekoException {
        if (parsedInput.length != 3) {
            throw new NekoException("Wrong format meow!");
        }
        return new Todo(taskName);
    }

    private Task createDeadlineTask(String[] parsedInput, String taskName) throws NekoException {
        if (parsedInput.length != 4) {
            throw new NekoException("Wrong format meow!");
        }
        LocalDateTime by = Parser.parseTime(parsedInput[3].trim());
        return new Deadline(taskName, by);
    }

    private Task createEventTask(String[] parsedInput, String taskName) throws NekoException {
        if (parsedInput.length != 5) {
            throw new NekoException("Wrong format meow!");
        }
        LocalDateTime start = Parser.parseTime(parsedInput[3].trim());
        LocalDateTime end = Parser.parseTime(parsedInput[4].trim());
        return new Event(taskName, start, end);
    }

    /**
     * Return a list of commands available to serve as a help guide.
     * @return A list of commands Neko offers.
     */
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
                + "9. view [date] - Displays the list of tasks scheduled for the specified date\n"
                + "10. help - Shows this list of commands\n";

    }

    /**
     * Handles the 'mark' command to mark multiple tasks as done.
     *
     * @param input The user input containing task numbers to mark as done.
     * @return A response string showing the tasks that were marked as done.
     * @throws NekoException If the task numbers are invalid.
     * @throws IOException If an I/O error occurs while rewriting the tasks file.
     */
    private String handleMarkCommand(String input) throws NekoException, IOException {
        Integer[] markIndices = Parser.parseIndices(input);
        Task[] tasksArray = tasks.markTask(markIndices);
        storage.rewriteFile(tasks);
        return formatTaskArrayResponse(tasksArray, "marked as done");
    }
    /**
     * Handles the 'unmark' command to unmark multiple tasks as done.
     *
     * @param input The user input containing task numbers to unmark.
     * @return A response string showing the tasks that were unmarked.
     * @throws NekoException If the task numbers are invalid.
     * @throws IOException If an I/O error occurs while rewriting the tasks file.
     */
    private String handleUnmarkCommand(String input) throws NekoException, IOException {
        Integer[] unmarkIndices = Parser.parseIndices(input);
        Task[] tasksArray = tasks.unmarkTask(unmarkIndices);
        storage.rewriteFile(tasks);
        return formatTaskArrayResponse(tasksArray, "marked as not done");
    }
    /**
     * Handles the 'delete' command to delete multiple tasks.
     *
     * @param input The user input containing task numbers to delete.
     * @return A response string showing the tasks that were deleted.
     * @throws NekoException If the task numbers are invalid.
     * @throws IOException If an I/O error occurs while rewriting the tasks file.
     */
    private String handleDeleteCommand(String input) throws NekoException, IOException {
        Integer[] deleteIndices = Parser.parseIndices(input);
        Task[] tasksArray = tasks.deleteTask(deleteIndices);
        storage.rewriteFile(tasks);
        return formatTaskArrayResponse(tasksArray, "delete");
    }

    /**
     * Handles the 'add' command to add a new task (Todo, Deadline, Event).
     *
     * @param input The user input containing the task details.
     * @return A response string showing the added task.
     * @throws NekoException If the input format is invalid or the task cannot be created.
     * @throws IOException If an I/O error occurs while writing to the tasks file.
     */
    private String handleAddCommand(String input) throws NekoException, IOException {
        Task task = createTask(input);
        if (task == null) {
            return ui.getInvalidInputMessage();
        }
        tasks.addTask(task);
        storage.writeFile(task);
        return "Purrfect! I've added this task meow ฅ/ᐠᓀ ﻌ ᓂマ\n "
                + task + "\nNow you have " + tasks.size() + " tasks in your list meow";
    }

    /**
     * Handles the 'find' command to search for tasks containing a specific keyword.
     *
     * @param input The user input containing the keyword.
     * @return A response string showing the tasks that match the keyword.
     * @throws NekoException If the keyword is invalid.
     */
    private String handleFindCommand(String input) throws NekoException {
        String key = Parser.parseKeyword(input).trim();
        String tasksFound = tasks.findTasks(key);
        if (tasksFound.isEmpty()) {
            return "No tasks found meow!";
        } else {
            return "Here are the matching tasks in your list meow:\n" + tasksFound;
        }
    }
    /**
     * Handles the 'view' command to search for tasks scheduled on specified date
     *
     * @param input The user input containing the date.
     * @return A response string showing the tasks scheduled on the date.
     * @throws NekoException If the input is invalid.
     */
    private String handleViewCommand(String input) throws NekoException {
        try {
            String dateStr = input.split(" ")[1];
            LocalDate date = Parser.parseDate(dateStr);
            return tasks.viewTasksOnDate(date);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NekoException("Please specify a date to view your schedule meow!");
        }
    }

    /**
     * Formats the response string for tasks that have been marked, unmarked, or deleted.
     *
     * @param tasks The array of tasks that were marked, unmarked, or deleted.
     * @param action The action performed (e.g., "marked as done", "deleted").
     * @return A formatted response string.
     */
    private String formatTaskArrayResponse(Task[] tasks, String action) {
        StringBuilder response = new StringBuilder("Nice meow! I've ");
        response.append(action).append(" the following tasks:\n");
        int count = 1;
        for (Task task : tasks) {
            if (task != null) {
                response.append(count++ + ". ").append(task).append("\n");
            }
        }
        return response.toString().trim();
    }
}
