package Milo.Ui;

import Milo.Tasks.Task;
import Milo.Tasks.TaskList;
import Milo.Tasks.TaskType;

import java.util.ArrayList;
import java.util.Scanner;

/*
* Milo Ui system
* sets how Milo interacts with user
* as well as passing on user input to Parser
 */
public class Ui {
    private final Scanner myScanner = new Scanner(System.in);
    private final String cat0 = """
                  ╱|、
                (˚ˎ 。7 \s
                 |、˜〵         \s
                 じしˍ,)ノ
                """;
    private final String cat1 = """
                 ∧,,,∧
                ( ̳• · •̳)
                /    づ♡
                """;
    private final String hLine = "____________________________________________________________\n";
    private final String greeting = "Hello! I'm Milo.Milo\nWhat can I do for you?\n" + cat0;
    private final String bye = "Bye. Hope to see you again soon!\n" + cat1;
    private final String greetingMessage = hLine + greeting + hLine;
    private final String byeMessage = hLine + bye + hLine;

    /*
    * Milo greets user
     */
    public void greetUser() {
        // Greet user
        System.out.println(greetingMessage);
    }

    /*
     * Milo gets user input
     */
    public String getUserInput() {
        return myScanner.nextLine();
    }

    /*
     * Milo says bye to user
     */
    public void byeUser() {
        System.out.print(byeMessage);
    }

    /*
     * Milo prints all task in the array list
     *
     * @param array of task
     */
    public void printList(TaskList todoList) {
        System.out.print(hLine);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < todoList.getNumberOfTasks(); i++) {
            System.out.println(i+1 + "." + todoList.get(i).toString());
        }
        System.out.println(hLine);
    }

    /*
     * Milo prints feedback message on marking completion
     *
     * @param task object that system marked as done
     */
    public void printMark(Task curTask) {
        System.out.print(hLine);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + curTask.toString());
        System.out.print(hLine);
    }

    /*
     * Milo prints feedback message on marking task as incomplete
     *
     * @param task object that system marked as not done
     */
    public void printUnmark(Task curTask) {
        System.out.print(hLine);
        System.out.println("Ok, I've marked this as not done yet:");
        System.out.println("  " + curTask.toString());
        System.out.print(hLine);
    }

    /*
     * Milo prints feedback message on marking task as incomplete
     *
     * @param task object that system deleted
     */
    public void printDelete(Task curTask, int numberOfTask) {
        System.out.print(hLine);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + curTask.toString());
        if (numberOfTask == 1) {
            System.out.println("Now you have " + numberOfTask + " task in the list.");
        } else {
            System.out.println("Now you have " + numberOfTask + " tasks in the list.");
        }
        System.out.print(hLine);
    }

    /*
    * Milo prints task details
    *
    * @param task object that system wants to print to user
     */
    public void printTask(Task curTask, int numberOfTask) {
        System.out.print(hLine);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + curTask.toString());
        if (numberOfTask == 1) {
            System.out.println("Now you have " + numberOfTask + " task in the list.");
        } else {
            System.out.println("Now you have " + numberOfTask + " tasks in the list.");
        }
        System.out.print(hLine);
    }

    public void printFoundTask(TaskList todoList, int tasksFounded) {
        System.out.print(hLine);
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasksFounded; i++) {
            System.out.println(i+1 + "." + todoList.get(i).toString());
        }
        System.out.println(hLine);
    }

    /*
    * Milo prints error
    *
    * @param task type of the task that caused the error
    * @param error description
     */

    public void printError(TaskType.taskType tasktype, String desc) {
        String oops = "OOPS!!! ";
        switch (tasktype) {
            case TODO, EVENT, DEADLINE:
                System.out.println(hLine + oops + desc + "\n" + hLine);
                break;
            case INVALID:
                String invalidMessage = oops + "I'm sorry, but I don't know what that means ;-;\n";
                System.out.println(hLine + invalidMessage + hLine);
                break;
            case DATE:
                String invalidDateMessage = oops + "date is not properly formatted\n " +
                        "It should be formatted as follow: YYYY-MM-DD\n";
                System.out.println(hLine + invalidDateMessage + hLine);
                break;
            default:
                String defaultMessage = oops + "I'm sorry, I don't know what that means ;-;\n";
                System.out.println(hLine + defaultMessage + hLine);
        }
    }
}
