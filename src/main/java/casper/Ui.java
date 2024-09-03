package casper;

import exception.CasperBotException;
import exception.CasperBotOutOfBoundsException;

/**
 * Represents the Ui class
 */
public class Ui {
    /**
     * Represents the enumeration of the various task commands
     */
    public enum TaskCommand {
        MARK, UNMARK, DELETE;
    }
    public Ui() {}

    public void printLine() {
        System.out.println("------------------------------------------");
    }

    public void greeting() {
        System.out.println("Hello! I'm CasperBot.\n" + "What can I do for you?");
    }

    /**
     * Prints the response after a command from the user
     * @param command The command that was executed
     * @param task The task that was modified
     */
    public void displayUpdateMessage(TaskCommand command, Task task) {
        switch (command) {
        case MARK:
            System.out.println("Nice! I've marked this task as done:");
            break;
        case UNMARK:
            System.out.println("OK, I've marked this task as not done yet:");
            break;
        case DELETE:
            System.out.println("Noted. I've removed this task:");
            break;
        default:
            break;
        }
        System.out.println("  " + task);
    }


    /**
     * Prints all tasks in the task list provided
     * @throws CasperBotOutOfBoundsException If getTask calls an index out of bounds
     */
    public void displayTaskList(TaskList taskList) throws CasperBotOutOfBoundsException {
        if (taskList.isEmpty()) {
            System.out.println("You currently have no tasks in your list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.getNumberOfTasks(); i++) {
                System.out.printf("%d. %s%n", i + 1, taskList.getTask(i));
            }
        }
    }

    public void addTaskMessage(Task task) {
        System.out.println("Got it. I've added this task:\n" + "  " + task);
    }

    /**
     * Displays a message regarding how many tasks are in the task list
     * @param length The length of the task list
     */
    public void printTaskListLength(int length) {
        if (length == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.printf("Now you have %d tasks in the list.\n", length);
        }
    }

    /**
     * Displays a message regarding how many matching tasks are in the task list
     * @param taskList The task list where matches are checked for
     * @throws CasperBotOutOfBoundsException
     */
    public void printMatchedTasks(TaskList taskList) throws CasperBotOutOfBoundsException {
        if (taskList.isEmpty()) {
            System.out.println("There are no matches in your list.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < taskList.getNumberOfTasks(); i++) {
                System.out.printf("%d. %s%n", i + 1, taskList.getTask(i));
            }
        }
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showErrorMessage(CasperBotException e) {
        System.out.println(e.getMessage());
    }
}
