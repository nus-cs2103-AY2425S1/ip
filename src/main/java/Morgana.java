import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Morgana {
    private static final String NAME = "Morgana";
    private static final String HORIZONTAL_LINE = "============================================================\n";

    private static final List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        System.out.print(HORIZONTAL_LINE);
        System.out.printf("Hello! I'm %s.%n", NAME);
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);

        Scanner sc = new Scanner(System.in);
        String line;
        while (!(line = sc.nextLine()).equals("bye")) {
            if (line.equals("list")) {
                listTasks();
            } else if (line.startsWith("mark")) {
                markTaskAsDone(Integer.parseInt(line.split(" ")[1]) - 1);
            } else if (line.startsWith("unmark")) {
                markTaskAsNotDone(Integer.parseInt(line.split(" ")[1]) - 1);
            } else {
                tasks.add(new Task(line));
                System.out.print(HORIZONTAL_LINE);
                System.out.printf("added: %s%n", line);
                System.out.println(HORIZONTAL_LINE);
            }
        }

        System.out.print(HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.print(HORIZONTAL_LINE);
    }

    private static void listTasks() {
        System.out.print(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, tasks.get(i));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private static void markTaskAsDone(int index) {
        Task task = tasks.get(index);
        task.markAsDone();

        System.out.print(HORIZONTAL_LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("%d. %s%n", index + 1, task);
        System.out.println(HORIZONTAL_LINE);
    }

    private static void markTaskAsNotDone(int index) {
        Task task = tasks.get(index);
        task.markAsNotDone();

        System.out.print(HORIZONTAL_LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.printf("%d. %s%n", index + 1, task);
        System.out.println(HORIZONTAL_LINE);
    }
}
