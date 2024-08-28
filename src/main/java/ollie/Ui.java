package ollie;

import ollie.exception.OllieException;
import ollie.exception.UnknownTaskTypeException;

import ollie.task.Deadline;
import ollie.task.Event;
import ollie.task.Todo;
import ollie.task.Task;

import java.util.Scanner;

import java.util.ArrayList;

/**
 * The Ui class handles all interactions with the user.
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
            case FIND: {
                String keyword = Parser.parseKeyword(userCommand);
                ArrayList<Task> matchingTasks = taskList.findTasksByKeyword(keyword);
                showFindResults(matchingTasks);
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
     * Displays the search results for the find command.
     *
     * @param matchingTasks The list of tasks that match the search keyword.
     */
    public void showFindResults(ArrayList<Task> matchingTasks) {
        System.out.println("____________________________________________________________");
        if (matchingTasks.isEmpty()) {
            System.out.println("There are no matching tasks in your list.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + "." + matchingTasks.get(i));
            }
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Closes the scanner.
     */
    public void close() {
        scanner.close();
    }

    /**
     * Sets the TaskList for this UI instance.
     *
     * @param taskList The ollie.TaskList to set.
     */
    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }
}