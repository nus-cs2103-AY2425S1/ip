package ollie;

import ollie.exception.OllieException;
import ollie.exception.UnknownTaskTypeException;
import ollie.task.Deadline;
import ollie.task.Event;
import ollie.task.Todo;

import java.util.Scanner;

/**
 * Handles all interactions with the user.
 */
public class Ui {
    private final Scanner scanner;
    private TaskList taskList;

    /**
     * Constructs a ollie.Ui instance with a new Scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Greets the user with a welcome message.
     */
    public void greeting() {
        System.out.println("Hello there! ☺ I'm OLLIE ☺");
        System.out.println("What can I do for you today? ☺");
    }

    /**
     * Bids farewell to the user with a goodbye message.
     */
    public void exit() {
        System.out.println("Bye. Have a great day. ☺");
        System.out.println("Hope to see you again soon! ☺");
    }

    /**
     * Shows the error message for loading tasks.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks. Starting with an empty task list.");
    }

    /**
     * Reads the next command from the user.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Responds to the user's command.
     *
     * @param userCommand The command entered by the user.
     */
    public void respond(String userCommand) {
        try {
            Command command = Parser.parse(userCommand);

            switch (command) {
                case GREETING:
                    greeting();
                    break;
                case EXIT:
                    exit();
                    break;
                case LIST:
                    taskList.listTasks();
                    break;
                case MARK: {
                    int taskNumber = Parser.parseTaskNumber(userCommand, 5);
                    taskList.markTaskAsDone(taskNumber);
                    break;
                }
                case UNMARK: {
                    int taskNumber = Parser.parseTaskNumber(userCommand, 7);
                    taskList.unmarkTaskAsDone(taskNumber);
                    break;
                }
                case DELETE: {
                    int taskNumber = Parser.parseTaskNumber(userCommand, 7);
                    taskList.deleteTask(taskNumber);
                    break;
                }
                case TODO:
                    taskList.addTask(Todo.createTask(userCommand));
                    break;
                case DEADLINE:
                    taskList.addTask(Deadline.createTask(userCommand));
                    break;
                case EVENT:
                    taskList.addTask(Event.createTask(userCommand));
                    break;
                default:
                    throw new UnknownTaskTypeException();
            }
        } catch (OllieException e) {
            showMessage(e.getMessage());
        }
    }

    /**
     * Closes the scanner.
     */
    public void close() {
        scanner.close();
    }

    /**
     * Sets the ollie.TaskList for this UI instance.
     *
     * @param taskList The ollie.TaskList to set.
     */
    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }
}