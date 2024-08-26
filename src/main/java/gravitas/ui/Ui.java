package gravitas.ui;

import gravitas.exception.DukeException;
import gravitas.parser.Parser;
import gravitas.tasklist.TaskList;

import java.util.Scanner;

/**
 * This class is used to handle the user interface of the program
 */
public class Ui {
    private String line;
    private String name;
    private String greet;
    private String header;
    private String footer;
    private String bye;

    private static String error = "OOPS!!! I'm sorry, but I don't know what that means :-(";

    /**
     * Constructor for the Ui class
     */
    public Ui() {
        this.line = "____________________________________________________________";
        this.name = "Gravitas";
        this.greet = this.line + "\n" + "Hello! I'm " + name +
                "\nWhat can I do for you?\n" + this.line + "\n";
        this.header = this.line;
        this.footer = this.line + "\n";
        this.bye = "Bye. Hope to see you again soon! \n" + this.line;
    }

    /**
     * This method is used to greet the user
     */
    public void greet() {
        System.out.println(greet);
    }

    /**
     * This method is used to display the task list and take in user input
     *
     * @param tasklist the task list to be displayed
     * @return boolean to check if the program should continue running
     */
    public boolean display(TaskList tasklist) {
        Scanner sc = new Scanner(System.in);
        String msg = sc.nextLine();
        String[] msgFrag = msg.split(" ", 2);
        System.out.println(header);
        Parser parse = new Parser();
        try {
            if (msg.equals("bye")) {
                sc.close(); //close scanner
                System.out.println(bye);
                return false;
            } else if (msg.equals("list")) {
                tasklist.printTaskList();
            } else if (msgFrag[0].equals("mark")) {
                parse.parseMark(tasklist, msg);
            } else if (msgFrag[0].equals("unmark")) {
                parse.parseUnmark(tasklist, msg);
            } else if (msgFrag[0].equals("deadline") || msgFrag[0].equals("event") || msgFrag[0].equals("todo")) {
                parse.parseTask(tasklist, msg);
            } else if (msgFrag[0].equals("delete")) {
                parse.parseDelete(tasklist, msg);
            } else {
                throw new DukeException(error);
            }
        } catch (DukeException e) {
            System.out.println(e);
        } finally {
            System.out.println(footer);
        }
        return true;
    }

    /**
     * This method is used to display the loading error message
     */
    public static void showLoadingError() {
        System.out.println(Ui.error);
    }

}
