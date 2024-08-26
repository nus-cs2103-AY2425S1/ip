import java.util.ArrayList;
import java.util.Scanner;
public class Gale {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private ArrayList<String> taskList;

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

    public void addToTasks(String task) {
        taskList.add(task);
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Whoosh! Task \"" + task + "\" added to my windy memory.");
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
    public void exit() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Aw, it's time for you to go huh?");
        System.out.println("Catch you on the next gust!");
        System.out.println(HORIZONTAL_LINE);
    }
}
