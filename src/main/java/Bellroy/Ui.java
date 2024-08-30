package Bellroy;

import java.util.Scanner;

/**
 * The Ui class handles reading inputs from the user and printing the output to the console
 */
public class Ui {
    private Scanner scanner = new Scanner(System.in);

    /**
     * prints the initial message to the user.
     */
    public void printWelcomeMessage() {
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Bellroy\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
    }

    /**
     * prints the farewell message when user closes the Chatbot
     */
    public void printByeMessage() {
        System.out.println("____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }

    /**
     * Asks the user for the next action to perform
     * @return input from the user
     */
    public String getInput() {
        return scanner.nextLine();
    }

    /**
     * prints a message to show that the task has been added
     * @param task to be added to the list
     * @param size number of tasks after adding this task
     */
    public void taskAddedMessage(Task task, int size) {
        System.out.printf("    ____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "     %s\n" +
                "     Now you have %d tasks in the list.\n" +
                "    ____________________________________________________________\n",task, size);
    }

    /**
     * Displays all tasks to the user in the form of a list
     * @param taskList all tasks in List form
     */
    public void printTaskList(TaskList taskList) {
        System.out.println("____________________________________________________________");
        for(int i = 0; i < taskList.size(); i++) {
            System.out.println("     " + (i + 1) + ". " + taskList.get(i).toString());
        }
        System.out.println("____________________________________________________________\n");
    }

    /**
     * prints out message to show that the task is marked as done
     * @param task to mark as done
     */
    public void markedDone(Task task) {
        System.out.println("    ____________________________________________________________\n" +
                "     Nice! I've marked this task as done:\n" +
                "     " + task.toString() + "\n" +
                "    ____________________________________________________________\n");
    }

    /**
     *  prints out message to show that the task is marked as undone
     * @param task to mark as undone
     */
    public void markedUndone(Task task) {
        System.out.println("    ____________________________________________________________\n" +
                "     OK, I've marked this task as not done yet:\n" +
                "     " + task.toString() + "\n" +
                "    ____________________________________________________________\n");
    }

    /**
     * prints out message to show that the task is deleted
     * @param task to be deleted
     * @param size number of tasks remaining after deletion
     */
    public void taskDeleted(Task task, int size) {
        System.out.printf("    ____________________________________________________________\n" +
                "     Got it. I've removed this task:\n" +
                "       " + task + "\n" +
                "     Now you have %d tasks in the list.\n" +
                "    ____________________________________________________________\n", size);
    }

}
