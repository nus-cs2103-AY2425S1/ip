package gale;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import gale.exception.GaleException;
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
            ui.showLoadingError();
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
            ui.showLoadingError();
            this.taskList = new TaskList();
        }
        this.isRunning = true;
    }

    /**
     * The main entry point for the Gale task manager application to run.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Gale gale = new Gale();
        gale.run();
    }

    /**
     * Saves the current list of tasks to storage.
     * <p>This method is called after each operation that modifies a task in the tasklist.</p>
     */
    public void saveTasks() {
        try {
            storage.saveTasks(taskList.getTaskList());
        } catch (IOException e) {
            ui.showException("Oops! The wind interfered with saving your tasks. Please try again.");
        }
    }

    /**
     * Runs the main loop of the program.
     * <p>This method reads user input and processes it accordingly until the user inputs 'bye'.</p>
     */
    public void run() {
        assert ui != null : "Gale not initialized properly";
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        while (isRunning) {
            String input;
            input = scanner.nextLine().trim();
            String response = getResponse(input);
            System.out.println(response);
            if (!isRunning) {
                break;
            }
            saveTasks();
        }
        scanner.close();
    }

    public String getResponse(String input) {
        try {
            assert input != null : "Input should not be null";
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
                assert task != null : "Task should not be null";
                taskList.addTask(task);
                return ui.showAddedTask(task, taskList.size());
            }
        } catch (GaleException e) {
            return ui.showException(e.getMessage());
        }
    }

    /**
     * Deletes a task from the tasklist, given the task number, and returns the deleted task as a String.
     * @param input user input in the form of 'delete (task number)'
     * @return the deleted task
     * @throws GaleException if the task number is not provided
     */
    public String deleteTask(String input) throws GaleException {
        String[] strA = input.split(" ");
        if (strA.length != 2) {
            throw new GaleException("Your task number got lost in the wind. Please use 'delete [task number]'");
        }
        int index = Integer.parseInt(strA[1]) - 1;
        if (index < 0 || index >= taskList.size()) {
            throw new GaleException("Oops! That task number is lost in the wind. Try again?");
        }
        Task task = taskList.getTask(index);
        taskList.deleteTask(index);
        return ui.showDeletedTask(task, taskList.size());
    }

    /**
     * Marks or un-marks a task as done, given the task number.
     * Returns the marked or un-marked task as a String.
     * @param input user input in the form of 'mark (task number)' or 'unmark (task number)'
     * @return the marked or un-marked task
     * @throws GaleException if the task number is not provided or if the task is already in the requested state
     */
    public String handleTaskMarking(String input) throws GaleException {
        String[] strA = input.split(" ");
        int index = Integer.parseInt(strA[1]) - 1;
        boolean isDone = strA[0].equals("mark");
        if (index >= 0 && index < taskList.size()) {
            Task task = taskList.getTask(index);
            if (task.status() == isDone) {
                throw new GaleException("Oops! This task is already marked as " + (isDone ? "done." : "not done."));
            } else {
                taskList.markTask(index, isDone);
                return ui.showMarkedTask(task, isDone);
            }
        } else {
            throw new GaleException("Oops! That task number is lost in the wind. Try again?");
        }
    }

    /**
     * Finds tasks that contain the keyword in their description.
     * Returns the list of found tasks as a String
     * @param input the user input in the form of 'find (keyword)'
     * @return the list of found tasks
     * @throws GaleException if the keyword is missing
     */
    public String findTasks(String input) throws GaleException {
        String[] strA = input.split(" ", 2);
        if (strA.length < 2 || strA[1].trim().isEmpty()) {
            throw new GaleException("Oops! Your keyword is lost in the wind. Please use 'find [keyword]'.");
        }
        String keyword = strA[1].trim();
        ArrayList<Task> foundTasks = taskList.findTasks(keyword);
        return ui.showFoundTasks(foundTasks, keyword);
    }

    /**
     * Returns the TaskList attribute of Gale.
     * @return the TaskList attribute of Gale
     */
    public TaskList getTaskList() {
        return this.taskList;
    }
}
