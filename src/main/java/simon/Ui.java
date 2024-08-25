package simon;

import java.util.Scanner;

public class Ui {
    final String HOR_LINE = "\t____________________________________________________________\n";
    final String WLC_MSG = " Hello! I'm simon.Simon \n" +
            " What can I do for you?\n";
    final String EXT_MSG = " Bye. Hope to see you again soon!";
    public void showWelcome () {

        System.out.println(WLC_MSG);
    }

    public void showExit() {

        System.out.print(printMessage(EXT_MSG));
    }

    public void showError(Error e) {
        System.out.print(printMessage("An error occurred while loading: " + e.getMessage()));
    }

    public void showTaskList(TaskList tasks) {
        System.out.print(HOR_LINE);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println("\t" + (i + 1) + ". " + task.toString());
        }
        System.out.print(HOR_LINE);
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().trim();
    }

    public void showTaskAdded(Task task, Integer size) {
        System.out.print(printMessage("Got it. I've added this task:\n" + "\t" + task.toString())
                + "\tNow you have " + size + " tasks in the list.");
    }

    public void showTaskMarked(Task task) {
        System.out.print(printMessage("\tNice! I've marked this task as done:\n" + "\t" + task.toString()));
    }

    public void showTaskUnmarked(Task task) {
        System.out.print(printMessage("\tOK, I've marked this task as not done yet:\n" + "\t" + task.toString()));
    }

    public void showTaskDeleted(Task task, int taskCount) {
        System.out.print(printMessage("Noted. I've removed this task:\n" + "\t" + task.toString())
                + "\tNow you have " + taskCount + " tasks in the list.");
    }

    private String printMessage(String msg) {
        return HOR_LINE + "\t" + msg + "\n" + HOR_LINE;
    }
    public void showLoadingError() {
        System.out.println("Error in loading");
    }
}
