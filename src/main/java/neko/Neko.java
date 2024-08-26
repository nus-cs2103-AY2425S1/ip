package neko;
import neko.task.Task;
import neko.task.TaskList;
import neko.ui.Ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;

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

    // Constants for commands and file path
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String ADD_COMMAND = "add";
    private static final String DELETE_COMMAND = "delete";
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
        while (!input.equals(EXIT_COMMAND)) {
            try {
                String command = Parser.parseCommand(input);
                handleInput(command, input);
            } catch (NekoException | IOException e) {
                ui.showMessage(e.getMessage());
            } finally {
                input = ui.getUserCommand();
            }
        }
        ui.showExitMessage();
    }

    /**
     * Processes the user input based on the parsed command.
     * Handles commands such as listing tasks, marking tasks as done,
     * unmarking tasks, deleting tasks, and adding tasks.
     *
     * @param command The command parsed from user input.
     * @param input The full user input string.
     * @throws NekoException If an unknown command is provided or the task cannot be marked/unmarked.
     * @throws IOException If there is an issue with reading/writing to the tasks file.
     */

    private void handleInput(String command, String input) throws NekoException, IOException {
        Task task;
        switch (command) {
            case LIST_COMMAND:
                tasks.listTasks(ui);
                break;
            case MARK_COMMAND:
                task = tasks.markTask(
                        Integer.parseInt(input.split(" ")[1]) - 1);
                if (task != null) {
                    ui.showMessage("Nice meow! I've marked this task as done:\n " + task);
                } else {
                    throw new NekoException("The task is already marked as done meow!");
                }
                break;
            case UNMARK_COMMAND:
                task = tasks.unmarkTask(
                        Integer.parseInt(input.split(" ")[1]) - 1);
                if (task != null) {
                    ui.showMessage("Ok meow, I've marked this task as not done yet:\n " + task);
                } else {
                    throw new NekoException("The task is not marked as done yet meow!");
                }
                break;
            case DELETE_COMMAND:
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                task = tasks.getTask(index);
                tasks.deleteTask(index);
                storage.rewriteFile(tasks);
                ui.showMessage("Noted meow. I've removed this task\n " + task +"\nNow you have "
                        + tasks.size() + " tasks in the list.");
                break;
            case ADD_COMMAND:
                ui.showMessage("Let's add a task meow!");
                tasks.addTask(ui.getTaskType(), ui, storage);
                break;
            default:
                throw new NekoException("Unknown command.");
        }
    }
}
