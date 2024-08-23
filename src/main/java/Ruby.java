import java.util.Scanner;

public class Ruby {

    private static final Task[] tasks = new Task[100];
    private static int taskCount = 0;
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
        for (int i = 0; i < taskCount; i++) {
            System.out.println("     " + (i + 1) + "." + tasks[i]);
        }
    }
    private static void markTask(String command) throws RubyException {
        int taskNumber = Integer.parseInt(command.split(" ")[1]) - 1;
        if (taskNumber >= 0 && taskNumber < taskCount) {
            tasks[taskNumber].markAsDone();
            System.out.println("     Nice! I've marked this task as done:");
            System.out.println("       " + tasks[taskNumber]);
        } else {
            throw new RubyException("     Invalid task number.");
        }
    }

    private static void unmarkTask(String command) throws RubyException {
        int taskNumber = Integer.parseInt(command.split(" ")[1]) - 1;
        if (taskNumber >= 0 && taskNumber < taskCount) {
            tasks[taskNumber].markAsNotDone();
            System.out.println("     OK, I've marked this task as not done yet:");
            System.out.println("       " + tasks[taskNumber]);
        } else {
            throw new RubyException("     Invalid task number.");
        }
    }

    private static void addToDoTask(String command) throws RubyException {
        String[] parts = command.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new RubyException("OOPS!!! The description of a todo cannot be empty.");
        }
        tasks[taskCount] = new Todo(parts[1].trim());
        taskCount++;
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + tasks[taskCount - 1]);
        System.out.println("     Now you have " + taskCount + " tasks in the list.");
    }

    private static void addDeadlineTask(String command) throws RubyException {
        String[] parts = command.split(" /by ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new RubyException("OOPS!!! The description of a deadline must include a date/time.");
        }
        tasks[taskCount] = new Deadline(parts[0].substring(9).trim(), parts[1].trim());
        taskCount++;
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + tasks[taskCount - 1]);
        System.out.println("     Now you have " + taskCount + " tasks in the list.");
    }

    private static void addEventTask(String command) throws RubyException {
        String[] parts = command.split(" /from ", 2);
        if (parts.length < 2 || !parts[1].contains(" /to ")) {
            throw new RubyException("OOPS!!! The description of an event must include start and end times.");
        }
        String[] eventParts = parts[1].split(" /to ", 2);
        tasks[taskCount] = new Event(parts[0].substring(6).trim(), eventParts[0].trim(), eventParts[1].trim());
        taskCount++;
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + tasks[taskCount - 1]);
        System.out.println("     Now you have " + taskCount + " tasks in the list.");
    }
}