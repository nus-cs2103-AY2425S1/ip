import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Jar\nWhat can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showResponse(String response) {
        System.out.println(response);
    }

    public void showTaskList(String taskList) {
        System.out.println("Here are the tasks in your list:\n" + taskList);
    }

    public void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
    }

    public void showTaskAdded(String taskContent) {
        System.out.println("Added: " + taskContent);
    }

    public void showTaskCount(int count) {
        System.out.println("Now you have " + count + " tasks in the list.");
    }
    
    public void showDeleteTask(Task task) {
        System.out.println("Noted. I've removed this task:\n" + task);
    }

}
