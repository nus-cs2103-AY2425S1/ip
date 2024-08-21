import java.util.ArrayList;
import java.util.Objects;
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
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.printf("%d.%s%n", i + 1, task);
            }
        }
        printLine();
    }

    private static void markTaskDone(ArrayList<Task> tasks, int taskNumber) {
        tasks.get(taskNumber).markAsDone();
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(taskNumber));
        printLine();
    }

    private static void unmarkTask(ArrayList<Task> tasks, int taskNumber) {
        tasks.get(taskNumber).unmark();
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + tasks.get(taskNumber));
        printLine();
    }

    public static void main(String[] args) throws KilluaException {
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

            try {
                switch (command.toLowerCase()) {
                    case "bye" -> {
                        bye();
                        flag = false;
                    }
                    case "list" -> list(tasks);
                    case "mark", "unmark" -> {
                        try {
                            int taskNumber = Integer.parseInt(argument);
                            if ("mark".equalsIgnoreCase(command)) {
                                markTaskDone(tasks, taskNumber - 1);
                            } else {
                                unmarkTask(tasks, taskNumber - 1);
                            }
                        } catch (NumberFormatException e) {
                            throw new KilluaException("Please provide a valid number! e.g., " + command + " 1");
                        } catch (IndexOutOfBoundsException e) {
                            throw new KilluaException("Task not found!");
                        }
                    }
                    case "todo" -> {
                        if (Objects.equals(argument, "")) {
                            throw new KilluaException("Todo description cannot be empty!");
                        }
                        Task todo = new Todo(argument);
                        tasks.add(todo);
                        add(tasks, todo);
                    }
                    case "deadline" -> {
                        if (Objects.equals(argument, "")) {
                            throw new KilluaException("Deadline description cannot be empty!");
                        }
                        try {
                            String[] strs = argument.split("/", 2);
                            String by = strs[1].substring(3).strip();
                            Task deadline = new Deadline(strs[0].strip(), by);
                            tasks.add(deadline);
                            add(tasks, deadline);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new KilluaException("Please use the correct format for deadlines: deadline <description> /by <date>");
                        }
                    }
                    case "event" -> {
                        if (Objects.equals(argument, "")) {
                            throw new KilluaException("Event description cannot be empty!");
                        }
                        try {
                            String[] strs = argument.split("/", 3);
                            String from = strs[1].substring(5).strip();
                            String to = strs[2].substring(3).strip();
                            Task event = new Event(strs[0].strip(), from, to);
                            tasks.add(event);
                            add(tasks, event);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new KilluaException("Please use the correct format for events: event <description> /from <start time> /to <end time>");
                        }
                    }
                    default -> throw new KilluaException("Invalid input! Try again.");
                }
            } catch (KilluaException e) {
                printLine();
                System.out.println(e.getMessage());
                printLine();
            }
        }

        scanner.close();
    }
}
