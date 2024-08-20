import java.util.ArrayList;
import java.util.Scanner;

public class Killua {
    private static final String LINE = "____________________________________________________________";

    private static void printLine() {
        System.out.println(LINE);
    }

    private static void add(ArrayList<Task> tasks, Task task){
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() +  " tasks in the list.");
        printLine();
    }

    private static void bye(){
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    private static void list(ArrayList<Task> tasks){
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.printf("%d.%s%n", i + 1, task);
        }
        printLine();
    }

    private static void markTaskDone(ArrayList<Task> tasks, int taskNumber) {
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        tasks.get(taskNumber).markAsDone();
        System.out.println("  " + tasks.get(taskNumber));
        printLine();
    }

    private static void unmarkTask(ArrayList<Task> tasks, int taskNumber) {
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        tasks.get(taskNumber).unmark();
        System.out.println("  " + tasks.get(taskNumber));
        printLine();
    }

    public static void main(String[] args) {
        printLine();
        System.out.println("Hello! I'm Killua");
        System.out.println("What can I do for you?");
        printLine();

        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while (flag) {
            String input = scanner.nextLine().trim();
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            String argument = (parts.length > 1) ? parts[1] : "";

            switch(command.toLowerCase()) {
                case "bye" -> {
                    bye();
                    flag = false;
                }
                case "list" -> list(tasks);
                case "mark" -> {
                    int taskNumber = Integer.parseInt(argument);
                    markTaskDone(tasks, taskNumber-1);
                }
                case "unmark" -> {
                    int taskNumber = Integer.parseInt(argument);
                    unmarkTask(tasks, taskNumber-1);
                }
                case "todo" -> {
                    Task todo = new Todo(argument);
                    tasks.add(todo);
                    add(tasks, todo);
                }
                case "deadline" -> {
                    String[] strs = argument.split("/", 2);
                    String by = strs[1].substring(3).strip();
                    Task deadline = new Deadline(strs[0].strip(), by);
                    tasks.add(deadline);
                    add(tasks, deadline);
                }
                case "event" -> {
                    String[] strs = argument.split("/", 3);
                    String from = strs[1].substring(5).strip();
                    String to = strs[2].substring(3).strip();
                    Task event = new Event(strs[0].strip(), from, to);
                    tasks.add(event);
                    add(tasks, event);
                }
            }
        }

        scanner.close();
    }
}
