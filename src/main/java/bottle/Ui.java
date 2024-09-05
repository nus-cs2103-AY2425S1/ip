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
    static final String LINE_BREAK = "\n____________________________________________________________\n";
    /**
     * The Welcome msg.
     */
    private final String welcomeMsg =
            " Hello! I'm bottle.Bottle\n"
                    + " What can I do for you?";
    /**
     * The Bye msg.
     */
    private final String byeMsg = " Bye. Hope to see you again soon!";
    /**
     * The Scanner.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Print with break.
     *
     * @param str the str
     */
    public String printWithBreak(String str) {
        return LINE_BREAK + str + LINE_BREAK;
    }

    /**
     * Print welcome msg.
     */
    public String printWelcomeMsg() {
        return printWithBreak(welcomeMsg);
    }

    /**
     * Print bye msg.
     */
    public String printByeMsg() {
        return printWithBreak(byeMsg);
    }

    /**
     * Print mark.
     *
     * @param task the task
     */
    public String printMark(Task task) {
        return printWithBreak("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Print un mark.
     *
     * @param task the task
     */
    public String printUnMark(Task task) {
        return printWithBreak("OK, I've marked this task as not done yet:\n" + task);
    }

    /**
     * Print task added message.
     *
     * @param taskList the task list
     */
    public String printTaskAddedMessage(ArrayList<Task> taskList) {
        return printWithBreak("Got it. I've added this task:\n   " + taskList.get(taskList.size() - 1)
                + "\nNow you have " + taskList.size() + " tasks in the list.");
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
    public String printTaskList(TaskList taskList) {
        return printWithBreak(taskList.toString());
    }

    /**
     * Print delete msg.
     *
     * @param taskList the task list
     * @param task     the task
     */
    public String printDeleteMsg(ArrayList<Task> taskList, Task task) {
        return printWithBreak("Got it. I've removd this task:\n   " + task
                + "\nNow you have " + taskList.size() + " tasks in the list.");
    }

}
