import java.util.Scanner;
import java.util.ArrayList;
public class Zero {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        int taskCount = 0;

        //intro message.
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Zero");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            try {
                if (input.equals("bye")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Baibai!");
                    System.out.println("____________________________________________________________");
                    break;
                } else if (input.equals("list")) {
                    listTasks(tasks);
                } else if (input.startsWith("delete")) {
                    handleDelete(tasks, input);
                } else if (input.startsWith("mark")) {
                    handleMark(tasks, input, taskCount);
                } else if (input.startsWith("unmark")) {
                    handleUnmark(tasks, input, taskCount);
                } else if (input.startsWith("todo")) {
                    handleTodo(tasks, input, taskCount);
                    taskCount++;
                } else if (input.startsWith("deadline")) {
                    handleDeadline(tasks, input, taskCount);
                    taskCount++;
                } else if (input.startsWith("event")) {
                    handleEvent(tasks, input, taskCount);
                    taskCount++;
                } else {
                    throw new ZeroException("分かりません");
                }
            } catch (ZeroException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" oopsie, " + e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }

    private static void listTasks(ArrayList<Task> tasks) {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    private static void handleDelete(ArrayList<Task> tasks, String input) throws ZeroException {
        try {
            String[] strArr = input.split(" ", 2);
            int index = Integer.parseInt(strArr[1]) - 1;
            if (index >= tasks.size() || index < 0) {
                throw new ZeroException("The task number is out of range.");
            }
            Task removedTask = tasks.remove(index);
            System.out.println("____________________________________________________________");
            System.out.println(" Noted. I've removed this task:");
            System.out.println("   " + removedTask);
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } catch (NumberFormatException e) {
            throw new ZeroException("Please enter a valid task number to delete.");
        }
    }

    //its getting abit too long so gonna shift it here
    private static void handleMark(ArrayList<Task> tasks, String input, int taskCount) throws ZeroException {
        try {
            String[] strArr = input.split(" ", 2);
            int choice = Integer.parseInt(strArr[1]) - 1;  // convert to zero-based index
            if (choice >= taskCount || choice < 0) {
                throw new ZeroException("The task number is out of range.");
            }
            tasks.get(choice).markAsDone();
            System.out.println("____________________________________________________________");
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + tasks.get(choice));
            System.out.println("____________________________________________________________");
        } catch (NumberFormatException e) {
            throw new ZeroException("PLease enter a valid task number to mark.");
        }
    }

    private static void handleUnmark(ArrayList<Task> tasks, String input, int taskCount) throws ZeroException {
        try {
            String[] strArr = input.split(" ", 2);
            int choice = Integer.parseInt(strArr[1]) - 1;  // convert to zero-based index
            if (choice >= taskCount || choice < 0) {
                throw new ZeroException("The task number is out of range.");
            }
            tasks.get(choice).markAsNotDone();
            System.out.println("____________________________________________________________");
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + tasks.get(choice));
            System.out.println("____________________________________________________________");
        } catch (NumberFormatException e) {
            throw new ZeroException("Please enter a valid task number to unmark.");
        }
    }

    private static void handleTodo(ArrayList<Task> tasks, String input, int taskCount) throws ZeroException {
        if (input.trim().equals("todo")) {
            throw new ZeroException("The description of a todo cannot be empty.");
        }
        String description = input.substring(5).trim();  // extract description
        tasks.add(new Todo(description));
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + tasks.get(taskCount));
        System.out.println(" Now you have " + (taskCount + 1) + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private static void handleDeadline(ArrayList<Task> tasks, String input, int taskCount) throws ZeroException {
        String[] parts = input.split("/by ", 2);
        if (parts.length < 2 || parts[0].trim().equals("deadline")) {
            throw new ZeroException("The description of a deadline or the date/time cannot be empty.");
        }
        String description = parts[0].substring(9).trim();  // extract description
        String by = parts[1].trim();  // extract deadline
        tasks.add(new Deadline(description, by));
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + tasks.get(taskCount));
        System.out.println(" Now you have " + (taskCount + 1) + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private static void handleEvent(ArrayList<Task> tasks, String input, int taskCount) throws ZeroException {
        String[] parts = input.split("/from | /to ");
        if (parts.length < 3 || parts[0].trim().equals("event")) {
            throw new ZeroException("The description of an event or the date/time cannot be empty.");
        }
        String description = parts[0].substring(6).trim();  // extract description
        String from = parts[1].trim();  // extract start time
        String to = parts[2].trim();  // extract end time
        tasks.add(new Event(description, from, to));
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + tasks.get(taskCount));
        System.out.println(" Now you have " + (taskCount + 1) + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
}
