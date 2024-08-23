import java.util.Scanner;
import Tasks.Task;
import Tasks.Event;
import Tasks.ToDo;
import Tasks.Deadline;

public class MrIncredible {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TaskStorage taskStorage = new TaskStorage();
    public static void main(String[] args) {
        MrIncredible.greet();

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                MrIncredible.sayBye();
                break;
            } else if (input.equals("list")) {
                MrIncredible.listTasks();
            } else if (input.length() >= 4 && input.substring(0, 4).equals("mark")) {
                MrIncredible.markTask(input);
            } else if (input.startsWith("todo ")) {
                MrIncredible.addToDoTask(input.substring(5));
            } else if (input.startsWith("deadline ")) {
                MrIncredible.addDeadlineTask(input.substring(9));
            } else if (input.startsWith("event ")) {
                MrIncredible.addEventTask(input.substring(6));
            } else {
                MrIncredible.addTask(input);
            }
        }

        scanner.close();
    }

    public static void addToDoTask(String description) {
        ToDo task = new ToDo(description);
        taskStorage.addTask(task);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + taskStorage.getTaskCount() + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    public static void addDeadlineTask(String input) {
        String[] parts = input.split(" /by ");
        String description = parts[0];
        String by = parts[1];

        Deadline task = new Deadline(description, by);
        taskStorage.addTask(task);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + taskStorage.getTaskCount() + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    public static void addEventTask(String input) {
        String[] parts = input.split(" /from | /to ");
        String description = parts[0];
        String from = parts[1];
        String to = parts[2];

        Event task = new Event(description, from, to);
        taskStorage.addTask(task);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + taskStorage.getTaskCount() + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    public static void greet() {
        String greeting = "    What can I do for you?";

        // Initial greeting
        System.out.println("    ____________________________________________________________");
        System.out.println("Hello! I'm MrIncredible");
        System.out.println(greeting);
        System.out.println("    ____________________________________________________________");
    }

    public static void sayBye() {
        String byeText = "    Bye. Hope to see you again soon!";

        System.out.println("    ____________________________________________________________");
        System.out.println(byeText);
        System.out.println("    ____________________________________________________________");
    }

    public static void markTask(String input) {
        String markTaskText = "    Nice! I've marked this task as done:";
        int numberToUpdate = Integer.parseInt(input.substring(5));
        taskStorage.markTask(numberToUpdate);

        System.out.println("    ____________________________________________________________");
        System.out.println(markTaskText);
        System.out.println("    " + taskStorage.getTask(numberToUpdate));
        System.out.println("    ____________________________________________________________");
    }

    public static void listTasks() {
        System.out.println("    ____________________________________________________________");
        taskStorage.listTasks();
        System.out.println("    ____________________________________________________________");
    }

    public static void addTask(String input) {
        Task task = new Task(input);
        taskStorage.addTask(task);
        System.out.println("    ____________________________________________________________");
        System.out.println("     added: " + task);
        System.out.println("    ____________________________________________________________");
    }
}
