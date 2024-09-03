package bottle;

import bottle.task.Task;
import bottle.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    final static String lineBreak = "\n____________________________________________________________\n";
    Scanner scanner = new Scanner(System.in);
    private final String welcomeMsg =
            " Hello! I'm bottle.Bottle\n" +
                    " What can I do for you?";

    private final String byeMsg =" Bye. Hope to see you again soon!";
    public void printWithBreak(String str) {
        System.out.println(lineBreak + str + lineBreak);
    }
    public void printWelcomeMsg() {
        printWithBreak(welcomeMsg);
    }
    public void printByeMsg() {
        printWithBreak(byeMsg);
    }

    public void printMark(Task task) {
        printWithBreak("Nice! I've marked this task as done:\n" + task);
    }
    public void printUnMark(Task task) {
        printWithBreak("OK, I've marked this task as not done yet:\n" + task);
    }

    public void printTaskAddedMessage(ArrayList<Task> taskList) {
        printWithBreak("Got it. I've added this task:\n   " + taskList.get(taskList.size() - 1) +
                "\nNow you have " + taskList.size() + " tasks in the list.");
    }
    public String getInput() {
        return scanner.nextLine();
    }
    public void printTaskList(TaskList taskList) {
        printWithBreak(taskList.toString());
    }

    public void printDeleteMsg(ArrayList<Task> taskList, Task task) {
        printWithBreak("Got it. I've removd this task:\n   " + task +
                "\nNow you have " + taskList.size() + " tasks in the list.");
    }

}
