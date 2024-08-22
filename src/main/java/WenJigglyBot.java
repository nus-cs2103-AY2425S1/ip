import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WenJigglyBot {
    static List<Task> tasks = new ArrayList<>(100);
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
            } else if (task.contains("mark") || task.contains("unmark")) {
                String[] strings = task.split(" ");
                String action = strings[0];
                int idx = Integer.parseInt(strings[1]) - 1;
                toggleTask(action, idx);
            } else {
                addTask(new Task(task));
            }
        }
        System.out.println("Goodbye!");
    }

    private static void toggleTask(String action, int idx) {
        Task task = tasks.get(idx);
        if (action.equals("mark")) {
            System.out.println("\tYay! Task Completed!");
            task.markTask();
            System.out.println("\t" + task);
        } else {
            System.out.println("\tGet to work boy, why not done!!!");
            tasks.get(idx).unmarkTask();
            System.out.println("\t" + task);
        }
    }

    private static void addTask(Task task) {
        tasks.add(task);
        System.out.printf("\tadded: %s\n", task.getDescription());
    }

    private static void displayTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("\t%d. %s%n", i+1, tasks.get(i).toString());
        }
    }
}
