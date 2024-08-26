import java.util.ArrayList;
import java.util.Scanner;
public class Gale {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private ArrayList<Task> taskList;

    public Gale() {
        this.taskList = new ArrayList<>();
    }
    public static void main(String[] args) {
        Gale gale = new Gale();
        gale.run();
    }

    public void run() {
        greet();
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                exit();
                break;
            } else if (input.equalsIgnoreCase("list")) {
                listTasks();
            } else if (input.startsWith("mark")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                markTask(taskIndex, true);
            } else if (input.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                markTask(taskIndex, false);
            } else {
                addToTasks(input);
            }
        }

        scanner.close();
    }
    public void greet() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Gale, your friendly windy assistant.");
        System.out.println("I'll keep your tasks on my tab. What do you have to do?");
        System.out.println(HORIZONTAL_LINE);
    }

    public void addToTasks(String description) {
        Task newTask = new Task(description);
        taskList.add(newTask);
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Whoosh! Task \"" + newTask.toString() + "\" added to my windy memory.");
        System.out.println("Anything else?");
        System.out.println(HORIZONTAL_LINE);
    }

    public void listTasks() {
        System.out.println(HORIZONTAL_LINE);
        if (taskList.isEmpty()) {
            System.out.println("No tasks breezing around now!");
        } else {
            System.out.println("You are breezy with these tasks:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + taskList.get(i));
            }
        }
        System.out.println(HORIZONTAL_LINE);
    }
    public void markTask(int index, boolean isDone) {
        if (index >= 0 && index < taskList.size()) {
            Task task = taskList.get(index);
            if (isDone) {
                task.markAsDone();
                System.out.println(HORIZONTAL_LINE);
                System.out.println("Good work! You breezed through this task!");
            } else {
                task.markAsNotDone();
                System.out.println(HORIZONTAL_LINE);
                System.out.println("Tough, this task is back in the windy realm.");
            }
            System.out.println("  " + task.toString());
        } else {
            System.out.println("Oops! That task number is lost in the wind. Try again?");
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public void exit() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Aw, it's time for you to go huh?");
        System.out.println("Catch you on the next gust!");
        System.out.println(HORIZONTAL_LINE);
    }
}
