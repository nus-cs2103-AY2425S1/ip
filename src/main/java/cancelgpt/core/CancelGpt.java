package cancelgpt.core;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import cancelgpt.command.Command;
import cancelgpt.exception.task.TaskDoesNotExist;
import cancelgpt.task.Task;

/**
 * Represents the chatbot.
 */
public class CancelGpt {
    private final String chatbotName;
    private final TasksList tasksList;
    private final CommandParser commandParser;
    private TasksStorage tasksStorage;

    /**
     * Initialises the chatbot with given storage directory path.
     *
     * @param storageDirPath the path to the directory that stores tasks data
     */
    public CancelGpt(Path storageDirPath) {
        this.chatbotName = "cancelgpt";
        this.tasksList = new TasksList();
        try {
            this.tasksStorage = new TasksStorage(this, storageDirPath);
        } catch (IOException e) {
            UI.printMessageToConsole("Unable to use TASKS STORAGE. Exiting program");
            System.exit(1);
        }
        this.commandParser = new CommandParser(this);
    }

    public static void main(String[] args) {
        CancelGpt cancelGpt = new CancelGpt(Paths.get(System
                .getProperty("user.home"), "accountexeregister-ip", "data"));
        cancelGpt.run();
    }

    /**
     * Gets the name of the chatbot.
     *
     * @return the chatbot name
     */
    public String getName() {
        return this.chatbotName;
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
        UI.printMessageToConsole("Hello! I am " + chatbotName);
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
        this.tasksList.deleteTask(taskNumber);
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
        UI.printMessageToConsole("Now you have " + this.tasksList.getSize() + " tasks in the list.");
    }

    /**
     * Marks the task identified by its task number in the chatbot's task list.
     * Does nothing if the task is already marked.
     *
     * @param taskNumber the task number in the chatbot's task list
     * @throws TaskDoesNotExist if the task number does not exist in the task list
     */
    public void markTask(int taskNumber) throws TaskDoesNotExist {
        this.tasksList.markTask(taskNumber);
    }

    /**
     * Unmarks the task identified by its task number in the chatbot's task list.
     * Does nothing if the task is already unmarked.
     *
     * @param taskNumber the task number in the chatbot's task list
     * @throws TaskDoesNotExist if the task number does not exist in the task list
     */
    public void unmarkTask(int taskNumber) throws TaskDoesNotExist {
        this.tasksList.unmarkTask(taskNumber);
    }

    /**
     * Adds the task to the chatbot's task list.
     *
     * @param task a Task object to be added
     */
    public String addToTaskList(Task task) {
        return this.tasksList.addToTaskList(task);
    }

    /**
     * Prints the tasks in the chatbot's task list to console.
     */
    public void displayTasksList() {
        this.tasksList.displayTasksList();
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
     * Displays a list of tasks with the keyword given in their description.
     *
     * @param keyword the keyword in the description of tasks to find
     */
    public void findTasks(String keyword) {
        UI.printMessageToConsole("Here are the matching tasks in your list:");

        List<Task> tasksFound = this.tasksList.findTasksByDescriptionKeyword(keyword);
        for (int i = 0; i < tasksFound.size(); i++) {
            UI.printMessageToConsole((i + 1) + "."
                    + tasksFound.get(i));
        }
    }

    /**
     * Returns the chatbot's task list
     *
     * @return the chatbot's task list
     */
    public List<Task> getTasks() {
        return this.tasksList.getTasksList();
    }
}
