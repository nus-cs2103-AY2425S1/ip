import java.util.ArrayList;

public class Ui {
    public void printTaskTypeAdded(Task task, int size) {
        printLine();
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t   " + task.toString());
        System.out.println("\t Now you have " + size + " tasks in the list.");
        printLine();
    }

    public void printMessage(String message) {
        System.out.println("\t " + message);
        printLine();
    }

    public void printHello() {
        printLine();
        printMessage("Hello! I'm Max\n\t What can I do for you?");
    }

    public void printBye() {
        printLine();
        printMessage("Bye. Hope to see you again soon!");
    }

    public void printLine() {
        System.out.println("\t____________________________________________________________");
    }

    public void list(ArrayList<Task> tasks) {
        printLine();
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int count = i + 1;
            System.out.println("\t " + count + "." + tasks.get(i).toString());
        }
        printLine();
    }

    public void printDeleteTask(Task removedTask, int size) {
        printLine();
        System.out.println("\t Noted. I've removed this task:");
        System.out.println("\t   " + removedTask.toString());
        System.out.println("\t Now you have " + size + " tasks in the list.");
        printLine();
    }

    public void printMarkDone(Task task) {
        printLine();
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t   " + task.toString());
        printLine();
    }

    public void printMarkNotDone(Task task) {
        printLine();
        System.out.println("\t OK, I've marked this task as not done yet:");
        System.out.println("\t   " + task.toString());
        printLine();
    }
}
