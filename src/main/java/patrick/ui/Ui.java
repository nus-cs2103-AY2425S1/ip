package patrick.ui;

import patrick.tasklist.Task;
import patrick.storage.Storage;

import java.io.*;
import java.util.Scanner;

public class Ui {
    static String HORIZONTAL_LINE = "____________________________________________________________\n";
    static String GREETING_MSG = HORIZONTAL_LINE + "Hello! I'm Patrick, Spongebob bestie\nHow can I help you?\n" + HORIZONTAL_LINE;
    static String EXIT_MSG = HORIZONTAL_LINE + "Bye. Hope to see you again soon!\n" + HORIZONTAL_LINE;
    static String TASK_MSG = "Got it. I've added this task:\n";
    static String NUM_TASK_MSG_1 = "Now you have ";
    static String NUM_TASK_MSG_2 = " tasks in the list.\n";
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void welcomeMessage() {
        System.out.println(GREETING_MSG);
        printFileContents();
    }

    public String getUserCommand() {
        out.print(HORIZONTAL_LINE + "What do you want to do: ");
        return in.nextLine();
    }

    public void showGoodbyeMsg() {
        out.print(HORIZONTAL_LINE + EXIT_MSG + HORIZONTAL_LINE);
    }

    public static void showErrorMsg(String error) {
        System.out.println(HORIZONTAL_LINE + error + HORIZONTAL_LINE);
    }

    public static void showUserMsg(String msg) {
        System.out.println(HORIZONTAL_LINE + Ui.TASK_MSG + msg + "\n" + Ui.NUM_TASK_MSG_1
                + Storage.getList().size() + Ui.NUM_TASK_MSG_2 + HORIZONTAL_LINE);
    }

    public static void showDeleteItemMsg(int num) {
        System.out.println(HORIZONTAL_LINE + "Noted. I've removed this task:\n"
                + Storage.getList().get(num - 1).toString() + "\n" + Ui.NUM_TASK_MSG_1 + (Storage.getList().size() - 1) + Ui.NUM_TASK_MSG_2);
    }

    public static void showMarkUnmarkMsg(String msg, String task) {
        System.out.println(HORIZONTAL_LINE + msg + task + HORIZONTAL_LINE);
    }

    public static void printFileContents() {
        System.out.println(Ui.HORIZONTAL_LINE + "Here are the tasks in your list:");
        for (int i = 1; i <= Storage.getList().size(); i++) {
            Task curr = (Task) Storage.getList().get(i - 1);
            System.out.println(i + ". " + curr.toString());
        }
        System.out.println(Ui.HORIZONTAL_LINE);
    }

    public static void formats() {
        System.out.println(HORIZONTAL_LINE + "Here are the different formats available:");
        System.out.println("yyyy-MM-dd HHmm");
        System.out.println("dd-MM-yyyy HHmm");
        System.out.println("d-MM-yyyy HHmm");
        System.out.println("MM-dd-yyyy HHmm");
        System.out.println("yyyy/MM/dd HHmm");
        System.out.println("dd/MM/yyyy HHmm");
        System.out.println("d/MM/yyyy HHmm");
        System.out.println("MM/dd/yyyy HHmm");
        System.out.println("MMM dd yyyy HHmm");
        System.out.println("MMM d yyyy HHmm");
    }

}
