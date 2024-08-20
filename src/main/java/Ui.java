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

    public void printAddTask(Task t) {
        this.printHorizontalLine();
        System.out.println("\tadded: " + t.toString());
        this.printHorizontalLine();
    }

    public void printTasks(ArrayList<Task> tasks) {
        this.printHorizontalLine();
        if (tasks.size() == 0) {
            System.out.println("\tNo tasks yet");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("\t" + (i + 1) + ". " + tasks.get(i).toString());
            }
        }
        this.printHorizontalLine();
    }
}