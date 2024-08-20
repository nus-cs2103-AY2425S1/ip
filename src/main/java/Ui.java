import java.util.ArrayList;

public class Ui {
    private final int lineLength = 50;
    private final String horizontalLine = "\t" + "-".repeat(this.lineLength);

    public void printHorizontalLine() {
        System.out.println(this.horizontalLine);
    }


    public void greet(String name) {
        this.printHorizontalLine();
        System.out.println("\t" + "Hello, I'm " + name);
        System.out.println("\t" + "What can I do for you?");
        this.printHorizontalLine();
    }

    public void exit() {
        this.printHorizontalLine();
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        this.printHorizontalLine();
    }

    public void printAddTask(Task t, int size) {
        this.printHorizontalLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + t);
        System.out.println("\tNow you have " + size + " tasks in the list.");
        this.printHorizontalLine();
    }

    public void printTasks(ArrayList<Task> tasks) {
        this.printHorizontalLine();
        if (tasks.size() == 0) {
            System.out.println("\tNo tasks yet");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("\t" + (i + 1) + "." + tasks.get(i).toString());
            }
        }
        this.printHorizontalLine();
    }

    public void printSuccessfulMark(Task t) {
        this.printHorizontalLine();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + t.toString());
        this.printHorizontalLine();
    }

    public void printSuccessfulUnmark(Task t) {
        this.printHorizontalLine();
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t\t" + t.toString());
        this.printHorizontalLine();
    }

    public void printFail() {
        this.printHorizontalLine();
        System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
        this.printHorizontalLine();
    }
}