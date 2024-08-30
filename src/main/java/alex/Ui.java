package alex;

import java.util.Scanner;

import alex.task.Task;

public class Ui {
    //Greet user
    private String greeting =
            """
                    ____________________________________________________________
                     Hello! I'm Alex, your personal assistant
                     What can I do for you today?
                    ____________________________________________________________""";

    //Farewell message
    private String farewell =
            """
                    ____________________________________________________________
                    Bye. Hope to see you again soon!
                    ____________________________________________________________""";

    //Create separation line
    private String line = "____________________________________________________________";

    private Scanner inputScanner = new Scanner(System.in);
    public void showWelcome() {
        System.out.println(this.greeting);
    }

    public void showGoodbye() {
        System.out.println(this.farewell);
    }

    public void showLine() {
        System.out.println(this.line);
    }

    public void showError(Exception e) {
        System.out.println(line + "\n" + e.getMessage() + "\n" + line);
    }

    public String readCommand() {
        //create new scanner for the line of user input
        return inputScanner.nextLine();
    }

    public void showTasks(TaskList tasks) {
        tasks.showTasks(this.line);
    }

    public void showMark(Task task) {
            System.out.println(line + "\nNice! I've marked this task as done: \n" + task + "\n" + line);
    }

    public void showUnmark(Task task) {
        System.out.println(line + "\nOK, I've marked this task as not done yet: \n" + task + "\n" + line);
    }

    public void showMessage(String str, Task task, int size) {
        System.out.println(this.line);
        System.out.println(str);
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list");
        System.out.println(this.line);
    }
}
