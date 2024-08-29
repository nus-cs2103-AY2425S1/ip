import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);

    public void startConversation() {
        showLine();
        System.out.println("Hello! I'm Lict");
        System.out.println("What can I do for you?");
        showLine();
    }

    public String readCommand() {
        String input = sc.nextLine().trim();
        while (input.isEmpty()) {
            System.out.println("Please enter a valid command.");
            input = sc.nextLine().trim();
            this.showLine();
        }
        return input;
    }

    public void showLine() {
        System.out.println("__________________________________");
    }


    public void showError(String message) {
        System.out.println(message);
    }

    public void showTaskList(TaskList tasks) {
        int size = tasks.size();
        if (size == 0) {
            System.out.println("There are no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < size; i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public void hasMarkedTask(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("    " + t);
    }

    public void hasUnmarkedTask(Task t) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("    " + t);
    }

    public void hasDeletedTask(Task t, int numOfTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("    " + t);
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
    }

    public void endConversation() {
        sc.close();
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    public void hasAddedTask(Task newTask, int numOfTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + newTask);
        System.out.println("Now you have " + numOfTasks + " in the list.");
    }
}
