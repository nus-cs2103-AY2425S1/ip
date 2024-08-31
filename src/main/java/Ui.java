import java.util.Scanner;

public class Ui {
    private Scanner sc;
    private String SEPARATOR = "------------------------------------------------------------------";
    public Ui() {
        this.sc = new Scanner(System.in);
    }
    public String readCommand() {
        return sc.nextLine();
    }

    public void sayWelcome() {
        System.out.println(SEPARATOR);
        System.out.println("Hello! I'm BabbleBot!\nWhat can I do for you?");
        System.out.println(SEPARATOR);
    }

    public void sayGoodbye() {
        System.out.println(SEPARATOR);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(SEPARATOR);
    }

    public void showTaskAdded(TaskList storedTasks) {
        System.out.println(SEPARATOR);
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + storedTasks.get(storedTasks.size() - 1).toString());
        System.out.println("Now you have " + storedTasks.size() + " tasks in the list.");
        System.out.println(SEPARATOR);
    }

    public void showRemoveMessage(TaskList storedTasks, int index) {
        System.out.println(SEPARATOR);
        System.out.println("Noted. I've removed this task:");
        System.out.println("   " + storedTasks.get(index).toString());
        storedTasks.deleteTask(index);
        System.out.println("Now you have " + storedTasks.size() + " tasks in the list.");
        System.out.println(SEPARATOR);
    }
    public void showTodoError() {
        System.out.println(SEPARATOR);
        System.out.println("OOPS!!! The description of a todo cannot be empty.");
        System.out.println(SEPARATOR);
    }

    public void showIOError() {
        System.out.println(SEPARATOR);
        System.out.println("OOPS!!! Something went wrong");
        System.out.println(SEPARATOR);
    }

    public void showMarkMessage(TaskList storedTasks, int index) {
        System.out.println(SEPARATOR);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("   " + storedTasks.get(index).toString());
        System.out.println(SEPARATOR);
    }

    public void showUnmarkMessage(TaskList storedTasks, int index) {
        System.out.println(SEPARATOR);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("   " + storedTasks.get(index).toString());
        System.out.println(SEPARATOR);
    }

    public void showBabbleBotError() {
        System.out.println(SEPARATOR);
        System.out.println("Whoopsie daisy! Looks like I got all tangled up in my words there!\n" +
                "Let's try that again in a way that might make a bit more sense.\n" +
                "What do you need help with?");
        System.out.println(SEPARATOR);
    }
}
