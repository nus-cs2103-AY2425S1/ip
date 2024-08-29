package knight2103;

import knight2103.tasks.Task;
import knight2103.tasks.TaskList;

import java.util.Scanner;

public class Ui {
    private final String welcome;
    private Scanner inputScanner;

    public Ui(String botName) { // Starting of bot programme
        this.welcome = showLine() + "\n"
                + "Hello! I'm " + botName + "\n"
                + "What can I do for you?\n"
                + showLine() + "\n";
        this.inputScanner = new Scanner(System.in);
    }

    public String showLine() {
        return "_____________";
    }

    public void showWelcome() {
        System.out.println(this.welcome);
    }

    public void showBye() {
        System.out.println(showLine() + "\n" + "Bye. Hope to see you again soon!" + "\n" + showLine());
    }

    public String readCommand() {
        return inputScanner.nextLine();
    }

    public void showLoadingError() {
        System.out.println("File cannot be loaded. Create taskList instead.");
    }

    public void showList(TaskList taskList) {
        System.out.println(showLine() + "\n" + taskList.printToList() + showLine());
    }

    public void showAdd(Task taskToAdd, TaskList taskList) {
        System.out.println(showLine() + "\nGot it. I've added this task:\n" + taskToAdd + "\n Now you have " + taskList.getSize() + " tasks in the list.\n" + showLine());
    }

    public void showMark(Task taskAffected) {
        System.out.println(showLine() + "\nNice! I've marked this task as done:\n" + taskAffected + "\n" + showLine());
    }

    public void showUnmark(Task taskAffected) {
        System.out.println(showLine() + "\nOK, I've marked this task as not done yet:\n" + taskAffected + "\n" + showLine());
    }

    public void showDelete(Task taskAffected, TaskList taskList) {
        System.out.println(showLine() + "\nNoted. I've removed this task:\n" + taskAffected + "\n Now you have " + taskList.getSize() + " tasks in the list.\n" + showLine());
    }

    public void showFind(TaskList taskList, String searchWord) {
        System.out.println(showLine() + "\nHere are the matching tasks in your list:\n"
                + taskList.searchPrintToList(searchWord) + showLine());
    }
}
