import java.util.ArrayList;
import java.util.Scanner;

public class SKD {
    private static final String LINE = "    ____________________________________________________________";
    private final ArrayList<Task> tasks;

    public SKD() {
        tasks = new ArrayList<>();
    }

    public void run() {
        greet();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            try {
                if (input.equals("bye")) {
                    bye();
                    break;
                } else if (input.equals("list")) {
                    System.out.println(input);
                    returnList();
                } else if (input.startsWith("mark")) {
                    System.out.println(input);
                    executeMark(input);
                } else if (input.startsWith("unmark")) {
                    System.out.println(input);
                    executeUnmark(input);
                } else if (input.startsWith("todo")) {
                    System.out.println(input);
                    executeToDo(input);
                } else if (input.startsWith("deadline")) {
                    System.out.println(input);
                    executeDeadline(input);
                } else if (input.startsWith("event")) {
                    System.out.println(input);
                    executeEvent(input);
                } else if (input.startsWith("delete")){
                    System.out.println(input);
                    executeDelete(input);
                } else {
                    System.out.println(input);
                    throw new IllegalArgumentException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(LINE);
                System.out.println("     " + e.getMessage());
                System.out.println(LINE);
                System.out.println();
            }
        }
        scanner.close();
    }

    private void greet() {
        System.out.println(LINE);
        System.out.println("     Hello! I'm SKD");
        System.out.println("     What can I do for you?");
        System.out.println(LINE);
        System.out.println();
    }

    private void bye() {
        System.out.println("bye");
        System.out.println(LINE);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    private void returnList() {
        System.out.println(LINE);
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println(LINE);
        System.out.println();
    }

    private void executeMark(String input) {
        System.out.println(LINE);
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new IllegalArgumentException("OOPS!!! The task number is invalid.");
            }
            tasks.get(index).markAsDone();
        } catch (IllegalArgumentException e) {
            System.out.println("     " + e.getMessage());
        }
        System.out.println(LINE);
        System.out.println();
    }

    private void executeUnmark(String input) {
        System.out.println(LINE);
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new IllegalArgumentException("OOPS!!! The task number is invalid.");
            }
            tasks.get(index).unmark();
        } catch (IllegalArgumentException e) {
            System.out.println("     " + e.getMessage());
        }
        System.out.println(LINE);
        System.out.println();
    }

    private void executeToDo(String input) {
        System.out.println(LINE);
        try {
            String description = input.length() > 5 ? input.substring(5).trim() : "";
            if (description.isEmpty()) {
                throw new IllegalArgumentException("OOPS!!! The description of a todo cannot be empty.");
            }
            ToDo task = new ToDo(description);
            tasks.add(task);
            printAddedMessage(task);
        } catch (IllegalArgumentException e) {
            System.out.println("     " + e.getMessage());
        }
        System.out.println(LINE);
        System.out.println();
    }

    private void executeDeadline(String input) {
        System.out.println(LINE);
        try {
            String[] parts = input.length() > 9 ? input.substring(9).split(" /by ") : new String[]{""};
            if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                throw new IllegalArgumentException("OOPS!!! The description or deadline cannot be empty.");
            }
            Deadline task = new Deadline(parts[0].trim(), parts[1].trim());
            tasks.add(task);
            printAddedMessage(task);
        } catch (IllegalArgumentException e) {
            System.out.println("     " + e.getMessage());
        }
        System.out.println(LINE);
        System.out.println();
    }

    private void executeEvent(String input) {
        System.out.println(LINE);
        try {
            String[] parts = input.length() > 6 ? input.substring(6).split(" /from | /to ") : new String[]{"", "", ""};
            if (parts.length < 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                throw new IllegalArgumentException("OOPS!!! The description, start time, or end time cannot be empty.");
            }
            Event task = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
            tasks.add(task);
            printAddedMessage(task);
        } catch (IllegalArgumentException e) {
            System.out.println("     " + e.getMessage());
        }
        System.out.println(LINE);
        System.out.println();
    }

    private void executeDelete(String input) {
        System.out.println(LINE);
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new IllegalArgumentException("OOPS!!! The task number is invalid.");
            }
            Task removedTask = tasks.remove(index);
            removedTask.printTaskRemovedMessage(tasks.size());
        } catch (IllegalArgumentException e) {
            System.out.println("     " + e.getMessage());
        }
        System.out.println(LINE);
        System.out.println();
    }

    private void printAddedMessage(Task task) {
        task.printTaskAddedMessage(tasks.size());
    }

    public static void main(String[] args) {
        new SKD().run();
    }
}