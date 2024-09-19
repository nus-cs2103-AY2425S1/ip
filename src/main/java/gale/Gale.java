package gale;
import java.io.IOException;
import java.util.Scanner;

import gale.exception.GaleException;
import gale.gui.MainWindow;
import gale.parser.Parser;
import gale.storage.Storage;
import gale.task.Task;
import gale.task.TaskList;
import gale.ui.Ui;

/**
 * A simple task manager that allows users to manage their tasks such as ToDo's,
 * Deadlines, and Events. Users can add, delete, list and mark or un-mark tasks as done.
 *
 * @author kaikquah
 */
public class Gale {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private boolean isRunning;
    private MainWindow mainWindow;

    /**
     * Creates a new Gale instance with the default file path to store the output.
     * <p>This constructor sets up the Ui and Storage instances, and loads the existing TaskList.</p>
     */
    public Gale() {
        this.ui = new Ui();
        this.storage = new Storage("src/main/java/gale/data/galeTasks.txt");
        try {
            this.taskList = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            mainWindow.displayError(ui.showLoadingError());
            this.taskList = new TaskList();
        }
        this.isRunning = true;
    }

    /**
     * Creates a new Gale instance with a specified file path to store the output.
     * <p>This constructor is used for testing purposes, to store the test output in a different
     * directory.</p>
     *
     * @param filePath where the output file is stored
     */
    public Gale(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            mainWindow.displayError(ui.showLoadingError());
            this.taskList = new TaskList();
        }
        this.isRunning = true;
    }

    /**
     * Creates a new Gale instance and starts the execution of the program.
     * <p>This method is only for the console version of the program.</p>
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Gale gale = new Gale();
        gale.run();
    }

    /**
     * Runs the main loop of the program.
     * <p>This method reads user input and processes it accordingly until the user inputs 'bye'.</p>
     * <p>This method is only for the console version of the program.</p>
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (isRunning) {
            String input;
            input = scanner.nextLine().trim();
            String response = getResponse(input);
            System.out.println(response);
            if (!isRunning) {
                break;
            }
            try {
                saveTasks();
            } catch (GaleException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }

    public String getResponse(String input) {
        try {
            if (input.equalsIgnoreCase("bye")) {
                this.isRunning = false;
                return ui.exit();
            } else if (input.equalsIgnoreCase("list")) {
                return ui.displayTaskList(taskList);
            } else if (input.startsWith("mark") || input.startsWith("unmark")) {
                return handleTaskMarking(input);
            } else if (input.startsWith("delete")) {
                return deleteTask(input);
            } else if (input.startsWith("find")) {
                return findTasks(input);
            } else {
                Task task = Parser.parseTask(input);
                taskList.addTask(task);
                return ui.showAddedTask(task, taskList.size());
            }
        } catch (GaleException e) {
            return ui.showException(e.getMessage());
        }
    }

    /**
     * Saves the current list of tasks to storage.
     * <p>This method is called after each operation that modifies a task in the tasklist.</p>
     */
    public void saveTasks() throws GaleException {
        try {
            storage.saveTasks(taskList.getTaskList());
        } catch (IOException e) {
            throw new GaleException("Oops! The wind interfered with saving your tasks. Please try again.");
        }
    }

    /**
     * Deletes a task from the tasklist and returns the deleted task as a String.
     * @param input user input in the form of 'delete (task number)'
     * @return the UI message for the deleted task as a String
     * @throws GaleException if the task number is invalid
     */
    public String deleteTask(String input) throws GaleException {
        int index = Parser.parseIndexFromCommand(input, "delete");
        if (index >= taskList.size()) {
            throw new GaleException("Oops! That task number is lost in the wind. Try again?");
        }
        Task task = taskList.deleteTask(index);
        return ui.showDeletedTask(task, taskList.size());
    }

    /**
     * Marks or un-marks a task as done, given the task number.
     * @param input user input in the form of 'mark (task number)' or 'unmark (task number)'
     * @return the UI message for the marked or unmarked task as a String
     * @throws GaleException if the task number is invalid or if the task is already in the requested state
     */
    public String handleTaskMarking(String input) throws GaleException {
        boolean isDone = input.startsWith("mark");
        String command = isDone ? "mark" : "unmark";
        int index = Parser.parseIndexFromCommand(input, command);
        if (index >= taskList.size()) {
            throw new GaleException("Oops! That task number is lost in the wind. Try again?");
        }
        Task task = taskList.markTask(index, isDone);
        return ui.showMarkedTask(task, isDone);
    }

    /**
     * Finds tasks that contain the keyword in their description.
     * @param input user input in the form of 'find (keyword)'
     * @return the UI message for the matching tasks as a String
     * @throws GaleException if the keyword is missing
     */
    public String findTasks(String input) throws GaleException {
        String keyword = Parser.parseKeyword(input);
        TaskList foundTasks = taskList.findTasks(keyword);
        return ui.showFoundTasks(foundTasks, keyword);
    }

    /**
     * Returns the TaskList attribute of Gale.
     * @return the TaskList attribute of Gale
     */
    public TaskList getTaskList() {
        return this.taskList;
    }

    /**
     * Returns the Ui field of Gale.
     * @return the Ui field of Gale
     */
    public Ui getUi() {
        return this.ui;
    }
}
