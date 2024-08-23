import java.util.Scanner;
import java.util.ArrayList;

public class Ruby {

    private static final ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Ruby\nWhat can I do for you?");

        while (true) {
            String command = scanner.nextLine().trim();
            try {
                if (command.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equals("list")) {
                    listTasks();
                } else if (command.startsWith("mark ")) {
                    markTask(command);
                } else if (command.startsWith("unmark ")) {
                    unmarkTask(command);
                } else if (command.startsWith("todo")) {
                    addToDoTask(command);
                } else if (command.startsWith("deadline")) {
                    addDeadlineTask(command);
                } else if (command.startsWith("event")) {
                    addEventTask(command);
                } else if (command.startsWith("delete")) {
                    deleteTask(command);
                } else {
                    throw new RubyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (RubyException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
    }
    private static void markTask(String command) throws RubyException {
        int taskNumber = Integer.parseInt(command.split(" ")[1]) - 1;
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            tasks.get(taskNumber).markAsDone();
            System.out.println("     Nice! I've marked this task as done:");
            System.out.println("       " + tasks.get(taskNumber));
        } else {
            throw new RubyException("     Invalid task number.");
        }
    }

    private static void unmarkTask(String command) throws RubyException {
        int taskNumber = Integer.parseInt(command.split(" ")[1]) - 1;
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            tasks.get(taskNumber).markAsNotDone();
            System.out.println("     OK, I've marked this task as not done yet:");
            System.out.println("       " + tasks.get(taskNumber));
        } else {
            throw new RubyException("     Invalid task number.");
        }
    }

    private static void addToDoTask(String command) throws RubyException {
        String[] parts = command.split(" ", 2);
        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new RubyException("OOPS!!! The description of a todo cannot be empty.");
        }
        tasks.add(new Todo(parts[1]));
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + tasks.get(tasks.size() - 1));
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addDeadlineTask(String command) throws RubyException {
        String[] parts = command.split(" /by ", 2);
        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new RubyException("OOPS!!! The description of a deadline must include a date/time.");
        }
        tasks.add(new Deadline(parts[0].substring(9), parts[1]));
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + tasks.get(tasks.size() - 1));
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addEventTask(String command) throws RubyException {
        String[] parts = command.split(" /from ", 2);
        if (parts.length < 2 || !parts[1].contains(" /to ")) {
            throw new RubyException("OOPS!!! The description of an event must include start and end times.");
        }
        String[] eventParts = parts[1].split(" /to ", 2);
        tasks.add(new Event(parts[0].substring(6), eventParts[0], eventParts[1]));
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + tasks.get(tasks.size() - 1));
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void deleteTask(String command) throws RubyException {
        int taskNumber = Integer.parseInt(command.split(" ")[1]) - 1;
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            Task removedTask = tasks.remove(taskNumber);
            System.out.println("     Noted. I've removed this task:");
            System.out.println("       " + removedTask);
            System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
        } else {
            throw new RubyException("     Invalid task number.");
        }
    }
}