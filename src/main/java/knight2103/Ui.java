package knight2103;

import knight2103.tasks.Task;
import knight2103.tasks.TaskList;

import java.util.Scanner;

public class Ui {
    private final String welcome;
    private Scanner inputScanner;

    public Ui(String botName) { // Starting of bot programme
        this.welcome =
                showLine() + "\n" + "Hello! I'm " + botName
                        + "\n" + "What can I do for you?\n" + showLine() + "\n";
        this.inputScanner = new Scanner(System.in);
    }

    public String showLine() {
        return "_____________";
    }

    public String showWelcome() {
        //System.out.println(this.welcome);
        return this.welcome;
    }

    public String showBye() {
        //System.out.println(showLine() + "\n" + "Bye. Hope to see you again soon!" + "\n" + showLine());
        return showLine() + "\n" + "Bye. Hope to see you again soon!" + "\n" + showLine();
    }

    public String readCommand() {
        return inputScanner.nextLine();
    }

    public String showLoadingError() {
        //System.out.println("File cannot be loaded. Create list of tasks instead.");
        return "File cannot be loaded. Create list of tasks instead.";
    }

    public String showList(TaskList tasks) {
        System.out.println(showLine() + "\n" + tasks.printToList() + showLine());
        return showLine() + "\n" + tasks.printToList() + showLine();
    }

    public String showAdd(Task taskToAdd, TaskList tasks) {
        /*System.out.println(showLine() + "\nGot it. I've added this task:\n" + taskToAdd + "\n Now you have "
                + tasks.getSize() + " tasks in the list.\n" + showLine());*/
        return showLine() + "\nGot it. I've added this task:\n" + taskToAdd + "\n Now you have "
                + tasks.getSize() + " tasks in the list.\n" + showLine();
    }

    public String showMark(Task taskAffected) {
        /*System.out.println(showLine() + "\nNice! I've marked this task as done:\n"
                + taskAffected + "\n" + showLine());*/
        return showLine() + "\nNice! I've marked this task as done:\n"
                + taskAffected + "\n" + showLine();
    }

    public String showUnmark(Task taskAffected) {
        /*System.out.println(showLine() + "\nOK, I've marked this task as not done yet:\n"
                + taskAffected + "\n" + showLine());*/
        return showLine() + "\nOK, I've marked this task as not done yet:\n"
                + taskAffected + "\n" + showLine();
    }

    public String showDelete(Task taskAffected, TaskList tasks) {
        /*System.out.println(showLine() + "\nNoted. I've removed this task:\n" + taskAffected
                + "\n Now you have " + tasks.getSize() + " tasks in the list.\n" + showLine());*/
        return showLine() + "\nNoted. I've removed this task:\n" + taskAffected
                + "\n Now you have " + tasks.getSize() + " tasks in the list.\n" + showLine();
    }

    public String showFind(TaskList tasks, String searchWord) {
        /* System.out.println(showLine() + "\nHere are the matching tasks in your list:\n"
                + tasks.searchPrintToList(searchWord) + showLine());*/
        return showLine() + "\nHere are the matching tasks in your list:\n"
                + tasks.searchPrintToList(searchWord) + showLine();
    }
}