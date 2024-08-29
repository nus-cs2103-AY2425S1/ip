package casper;

import exception.CasperBotException;
import exception.CasperBotOutOfBoundsException;

public class Ui {
    public enum TaskCommand {
        MARK, UNMARK, DELETE;
    }
    public Ui() {}

    public void printLine() {
        System.out.println("------------------------------------------");
    }

    public void greeting() {
        System.out.println("Hello! I'm CasperBot.\n" +
                "What can I do for you?");
    }

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
        System.out.println("Got it. I've added this task:\n" +
                "  " + task);
    }

    public void printTaskListLength(int length) {
        if (length == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.printf("Now you have %d tasks in the list.\n", length);
        }
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showErrorMessage(CasperBotException e) {
        System.out.println(e.getMessage());
    }
}
