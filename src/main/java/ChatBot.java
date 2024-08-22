import java.util.ArrayList;
import java.util.Scanner;

public class ChatBot {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        tasks.add(new Task("read book"));
        tasks.add(new Task("return book"));
        tasks.add(new Task("buy bread"));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm chatbot lisWhat can I do for you?");

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.startsWith("list")) {
                listTasks();
            } else if (input.startsWith("mark")) {
                int taskNumber = Integer.parseInt(input.substring(5)) - 1;
                markTask(taskNumber);
            } else if (input.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(input.substring(7)) - 1;
                unmarkTask(taskNumber);
            } else if (input.equalsIgnoreCase("exit")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println("I'm not sure what you mean. Can you please clarify?");
            }
        }
        scanner.close();
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    private static void markTask(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.get(taskIndex).markDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks.get(taskIndex));
        } else {
            System.out.println("Invalid task number!");
        }
    }

    private static void unmarkTask(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.get(taskIndex).unmarkDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks.get(taskIndex));
        } else {
            System.out.println("Invalid task number!");
        }
    }
}
