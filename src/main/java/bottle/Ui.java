package bottle;

import java.util.ArrayList;
import java.util.Scanner;

import bottle.task.Task;
import bottle.task.TaskList;

/**
 * The type Ui.
 */
public class Ui {
    /**
     * The constant lineBreak.
     */
    final static String lineBreak = "\n____________________________________________________________\n";
    /**
     * The Welcome msg.
     */
    private final String welcomeMsg =
            " Hello! I'm bottle.Bottle\n" +
                    " What can I do for you?";
    /**
     * The Bye msg.
     */
    private final String byeMsg = " Bye. Hope to see you again soon!";
    /**
     * The Scanner.
     */
    Scanner scanner = new Scanner(System.in);

    /**
     * Print with break.
     *
     * @param str the str
     */
    public void printWithBreak(String str) {
        System.out.println(lineBreak + str + lineBreak);
    }

    /**
     * Print welcome msg.
     */
    public void printWelcomeMsg() {
        printWithBreak(welcomeMsg);
    }

    /**
     * Print bye msg.
     */
    public void printByeMsg() {
        printWithBreak(byeMsg);
    }

    /**
     * Print mark.
     *
     * @param task the task
     */
    public void printMark(Task task) {
        printWithBreak("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Print un mark.
     *
     * @param task the task
     */
    public void printUnMark(Task task) {
        printWithBreak("OK, I've marked this task as not done yet:\n" + task);
    }

    /**
     * Print task added message.
     *
     * @param taskList the task list
     */
    public void printTaskAddedMessage(ArrayList<Task> taskList) {
        printWithBreak("Got it. I've added this task:\n   " + taskList.get(taskList.size() - 1) +
                "\nNow you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Gets input.
     *
     * @return the input
     */
    public String getInput() {
        return scanner.nextLine();
    }

    /**
     * Print task list.
     *
     * @param taskList the task list
     */
    public void printTaskList(TaskList taskList) {
        printWithBreak(taskList.toString());
    }

    /**
     * Print delete msg.
     *
     * @param taskList the task list
     * @param task     the task
     */
    public void printDeleteMsg(ArrayList<Task> taskList, Task task) {
        printWithBreak("Got it. I've removd this task:\n   " + task +
                "\nNow you have " + taskList.size() + " tasks in the list.");
    }

}
