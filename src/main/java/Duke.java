import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____  _            _                _   \n"
                + "|  _ \\| |          | |              | |  \n"
                + "| |_) | | __ _  ___| | ___ __  _   _| |_ \n"
                + "|  _ <| |/ _` |/ __| |/ / '_ \\| | | | __|\n"
                + "| |_) | | (_| | (__|   <| | | | |_| | |_ \n"
                + "|____/|_|\\__,_|\\___|_|\\_\\_| |_|\\__,_|\\__|\n";

        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Blacknut");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            String input = scanner.nextLine().trim();

            System.out.println("____________________________________________________________");

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                listTasks(tasks);
            } else if (input.startsWith("mark ")) {
                markTask(tasks, input, true);
            } else if (input.startsWith("unmark ")) {
                markTask(tasks, input, false);
            } else {
                addTask(tasks, input);
            }

            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }

    private static void listTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println(" The list is empty.");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
        }
    }

    private static void markTask(ArrayList<Task> tasks, String input, boolean markAsDone) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index >= 0 && index < tasks.size()) {
                Task task = tasks.get(index);
                if (markAsDone) {
                    task.markAsDone();
                    System.out.println(" Nice! I've marked this task as done:");
                } else {
                    task.markAsNotDone();
                    System.out.println(" OK, I've marked this task as not done yet:");
                }
                System.out.println("   " + task);
            } else {
                System.out.println(" Invalid task number. Please try again.");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println(" Invalid command. Please try again.");
        }
    }

    private static void addTask(ArrayList<Task> tasks, String input) {
        Task newTask = new Task(input);
        tasks.add(newTask);
        System.out.println(" added: " + newTask);
    }
}