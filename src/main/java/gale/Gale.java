package gale;
import java.util.Scanner;
import java.io.IOException;

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

    /**
     * Creates a new Gale instance with the default file path to store the output.
     * <p>This constructor sets up the Ui and Storage instances, and loads the existing TaskList.</p>
     */
    public Gale() {
        this.ui = new Ui();
        this.storage = new Storage("src/main/java/data/galeTasks.txt");
        try {
            this.taskList = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            ui.showLoadingError();
            this.taskList = new TaskList();
        }
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
    protected void saveTasks() {
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
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input;
            try {
                input = scanner.nextLine().trim();
                if (input.equalsIgnoreCase("bye")) {
                    ui.exit();
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    ui.displayTaskList(taskList);
                } else if (input.startsWith("mark") || input.startsWith("unmark")) {
                    handleTaskMarking(input);
                } else if (input.startsWith("delete")) {
                    deleteTask(input);
                } else {
                    Task task = Parser.parseTask(input);
                    taskList.addTask(task);
                    ui.printAddedTask(task, taskList.size());
                }
                saveTasks();
            } catch (GaleException e) {
                ui.showException(e.getMessage());
            }
        }
        scanner.close();
    }

    /**
     * Deletes a task from the tasklist, given the task number, and prints out the deleted task.
     * @param input user input in the form of 'delete (task number)'
     * @throws GaleException if the task number is not provided
     */
    public void deleteTask(String input) throws GaleException {
        String[] strA = input.split(" ");
        if (strA.length != 2) {
            throw new GaleException("Your task number got lost in the wind. Please use 'delete [task number]'");
        }
        int index = Integer.parseInt(strA[1]) - 1;
        Task task = taskList.getTask(index);
        taskList.deleteTask(index);
        ui.showDeletedTask(task, taskList.size());
    }

    /**
     * Marks or un-marks a task as done, given the task number.
     * @param input user input in the form of 'mark (task number)' or 'unmark (task number)'
     * @throws GaleException if the task number is not provided or if the task is already in the requested state
     */
    public void handleTaskMarking(String input) throws GaleException {
        String[] strA = input.split(" ");
        int index = Integer.parseInt(strA[1]) - 1;
        boolean isDone = strA[0].equals("mark");
        if (index >= 0 && index < taskList.size()) {
            Task task = taskList.getTask(index);
            if (task.status() == isDone) {
                throw new GaleException("Oops! This task is already marked as " + (isDone ? "done." : "not done."));
            } else {
                taskList.markTask(index, isDone);
                ui.showMarkedTask(task, isDone);
            }
        } else {
            throw new GaleException("Oops! That task number is lost in the wind. Try again?");
        }
    }
}
