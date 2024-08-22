import java.util.ArrayList;
import java.util.Scanner;

public class Oliver {
    private static ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("Hello! I'm Oliver.");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            String command = input.split(" ")[0];

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                handleList();
            } else if (command.equalsIgnoreCase("mark")) {
                handleMark(input);
            } else if (command.equalsIgnoreCase("unmark")) {
                handleUnmark(input);
            } else if (command.equalsIgnoreCase("todo")) {
                handleTodo(input);
            } else if (command.equalsIgnoreCase("deadline")) {
                handleDeadline(input);
            } else if (command.equalsIgnoreCase("event")) {
                handleEvent(input);
            } else if (command.equalsIgnoreCase("delete")) {
                handleDelete(input);
            } else {
                System.out.println("\tInvalid command. Command was not recognised.");
            }
        }
        sc.close();
    }

    public static void addSuccess(Task t) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t" + t);
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
    }

    public static void handleList() {
        if (tasks.isEmpty()) {
            System.out.println("\tThere are no tasks in your list.");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("\t" + (i+1) + "." + tasks.get(i));
            }
        }
    }

    public static void handleMark(String input) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index > tasks.size() - 1 || index < 0) {
                System.out.println("\tNo such task exists. Task number out of range.");
            } else {
                tasks.get(index).markAsDone();
                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t" + tasks.get(index));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tMissing arguments for this command.");
        } catch (NumberFormatException e) {
            System.out.println("\tInvalid arguments provided for this command.");
        }
    }

    public static void handleUnmark(String input) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index > tasks.size() - 1 || index < 0) {
                System.out.println("\tNo such task exists. Task number out of range.");
            } else {
                tasks.get(index).markAsUndone();
                System.out.println("\tOk, I've marked this task as not done yet:");
                System.out.println("\t" + tasks.get(index));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tMissing arguments for this command.");
        } catch (NumberFormatException e) {
            System.out.println("\tInvalid arguments provided for this command.");
        }
    }

    public static void handleTodo(String input) {
        try {
            ToDo t = new ToDo(input.split(" ", 2)[1]);
            tasks.add(t);
            addSuccess(t);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tMissing arguments for this command.");
        }
    }

    public static void handleDeadline(String input) {
        try {
            String[] parts = input.split("/by ");
            String time = parts[1];
            String action = parts[0].trim();
            Deadline d = new Deadline(action.split(" ", 2)[1], time);
            tasks.add(d);
            addSuccess(d);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tMissing arguments for this command.");
        }
    }

    public static void handleEvent(String input) {
        try {
            String[] parts = input.split("/from |/to ");
            String action = parts[0].trim();
            String start = parts[1].trim();
            String end = parts[2].trim();
            Event e = new Event(action.split(" ", 2)[1], start, end);
            tasks.add(e);
            addSuccess(e);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tMissing arguments for this command.");
        }
    }

    public static void handleDelete(String input) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index > tasks.size() - 1 || index < 0) {
                System.out.println("\tNo such task exists. Task number out of range.");
            } else {
                Task removedTask = tasks.get(index);
                tasks.remove(index);
                System.out.println("\tOk, I've removed this task:");
                System.out.println("\t" + removedTask);
                System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tMissing arguments for this command.");
        } catch (NumberFormatException e) {
            System.out.println("\tInvalid arguments provided for this command.");
        }
    }
}
