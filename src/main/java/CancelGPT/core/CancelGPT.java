package CancelGPT.core;

import CancelGPT.command.Command;
import CancelGPT.exception.task.TaskDoesNotExist;
import CancelGPT.task.Task;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the chatbot.
 */
public class CancelGPT {
    private final String CHATBOT_NAME;
    private final TasksList TASKS_LIST;
    private TasksStorage tasksStorage;
    private CommandParser commandParser;

    /**
     * Initialises the chatbot with given storage directory path.
     * 
     * @param storageDirPath the path to the directory that stores tasks data
     */
    public CancelGPT(Path storageDirPath) {
        this.CHATBOT_NAME = "CancelGPT";
        this.TASKS_LIST = new TasksList();
        try {
            this.tasksStorage = new TasksStorage(this, storageDirPath);
        } catch (IOException e) {
            UI.printMessageToConsole("Unable to use TASKS STORAGE. Exiting program");
            System.exit(1);
        }
        this.commandParser = new CommandParser(this);
    }

    /**
     * Gets the name of the chatbot.
     * 
     * @return the chatbot name
     */
    public String getName() {
        return this.CHATBOT_NAME;
    }

    public static void main(String[] args) {
        CancelGPT cancelGPT = new CancelGPT(Paths.get(System.getProperty("user.home"), "accountexeregister-ip", "data"));
        cancelGPT.run();
    }

    /**
     * The entrypoint for the chatbot.
     * Reads commands from the user and perform operations based on command given.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);

        UI.printHorizontalLineToConsole();
        greet();
        UI.printHorizontalLineToConsole();

        String command = sc.nextLine();
        while (!command.equals(Command.BYE.toString())) {
            UI.printHorizontalLineToConsole();
            handleCommand(command);
            UI.printHorizontalLineToConsole();

            try {
                saveTasks();
            } catch (IOException e) {
                UI.printMessageToConsole("Unable to save tasks to TASKS STORAGE. Exiting program");
                System.exit(1);
            }

            command = sc.nextLine();
        }

        sc.close();
        UI.printHorizontalLineToConsole();
        exit();
        UI.printHorizontalLineToConsole();
    }

    /**
     * Prints greeting message to the .
     */
    public void greet() {
        UI.printMessageToConsole("Hello! I am " + CHATBOT_NAME);
        UI.printMessageToConsole("What can I do for you?");
    }

    /**
     * Terminates the chatbot and prints a goodbye message.
     */
    public void exit() {
        UI.printMessageToConsole("Good bye. Hope to see you again soon!");
    }

    /**
     * Parses and handles the command given.
     * 
     * @param command command entered by user
     */
    public void handleCommand(String command) {
        this.commandParser.parseAndHandle(command);
    }

    /**
     * Deletes a task identified by its task number in the chatbot's task list.
     * 
     * @param taskNumber the task number in the chatbot's task list
     * @throws TaskDoesNotExist if the task number does not exist in the task list
     */
    public void deleteTask(int taskNumber) throws TaskDoesNotExist {
        this.TASKS_LIST.deleteTask(taskNumber);
    }

    /**
     * Handles adding the task to the chatbot's task list.
     * Prints message of adding task to console, and calls addToTaskList
     * 
     * @param task a Task object to be added
     */
    public void handleAddingTask(Task task) {
        UI.printMessageToConsole("Got it. I've added this task:");
        UI.printMessageToConsole(" " + this.addToTaskList(task));
        UI.printMessageToConsole("Now you have " + this.TASKS_LIST.getSize() + " tasks in the list.");
    }

    /**
     * Marks the task identified by its task number in the chatbot's task list.
     * Does nothing if the task is already marked.
     * 
     * @param taskNumber the task number in the chatbot's task list
     * @throws TaskDoesNotExist if the task number does not exist in the task list
     */
    public void markTask(int taskNumber) throws TaskDoesNotExist {
        this.TASKS_LIST.markTask(taskNumber);
    }

    /**
     * Unmarks the task identified by its task number in the chatbot's task list.
     * Does nothing if the task is already unmarked.
     *
     * @param taskNumber the task number in the chatbot's task list
     * @throws TaskDoesNotExist if the task number does not exist in the task list
     */
    public void unmarkTask(int taskNumber) throws TaskDoesNotExist {
        this.TASKS_LIST.unmarkTask(taskNumber);
    }

    /**
     * Adds the task to the chatbot's task list.
     *
     * @param task a Task object to be added
     */
    public String addToTaskList(Task task) {
        return this.TASKS_LIST.addToTaskList(task);
    }

    /**
     * Prints the tasks in the chatbot's task list to console.
     */
    public void displayTasksList() {
        this.TASKS_LIST.displayTasksList();
    }

    /**
     * Saves all the tasks to the chatbot's storage in the chatbot's given
     * by its storage path directory, so that it can be
     * retrieved in subsequent sessions of using the chatbot with the same
     * storage path directory given.
     * 
     * @throws IOException if the tasks cannot be saved
     */
    public void saveTasks() throws IOException {
        this.tasksStorage.saveTasks();
    }

    /**
     * Returns the chatbot's task list
     * 
     * @return the chatbot's task list
     */
    public List<Task> getTasks() {
        return this.TASKS_LIST.getTasksList();
    }
}
