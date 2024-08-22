import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WenJigglyBot {
    static List<String> tasks = new ArrayList<>(100);
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = "WenJigglyBot";
        System.out.println("Sup im " + name);
        System.out.println("What can I do for you?");
        String task = "";
        while (!task.equals("bye")) {
            task = scanner.nextLine();
            if (task.equals("list")) {
                displayTasks();
            } else {
                addTask(task);
            }
        }
        System.out.println("Goodbye!");
    }

    private static void addTask(String task) {
        tasks.add(task);
        System.out.printf("\tadded: %s\n", task);
    }

    private static void displayTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("\t%d. %s%n", i+1, tasks.get(i));
        }
    }
}
