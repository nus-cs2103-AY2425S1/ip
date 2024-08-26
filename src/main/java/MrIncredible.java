import java.util.Scanner;

public class MrIncredible {
    private static final Scanner scanner = new Scanner(System.in);
    private static final HarddiskStorage harddiskStorage = new HarddiskStorage("./data/duke.txt");
    private static final TaskStorage taskStorage = new TaskStorage(harddiskStorage);
    public static void main(String[] args) {
        MrIncredible.greet();

        while (true) {
            String input = scanner.nextLine().trim();

            if (input.equals("bye")) {
                MrIncredible.sayBye();
                break;
            } else if (input.equals("list")) {
                MrIncredible.listTasks();
            } else if (input.startsWith("mark ")) {
                MrIncredible.markTask(input);
            } else if (input.startsWith("todo ")) {
                if (input.length() > 5) {
                    MrIncredible.addToDoTask(input.substring(5).trim());
                } else {
                    MrIncredible.handleError("The description of a todo cannot be empty.");
                }
            } else if (input.startsWith("deadline ")) {
                if (input.length() > 9 && input.contains(" /by ")) {
                    MrIncredible.addDeadlineTask(input.substring(9).trim());
                } else {
                    MrIncredible.handleError("The deadline description or date is missing or improperly formatted. Use: deadline <description> /by <time>");
                }
            } else if (input.startsWith("event ")) {
                if (input.length() > 6 && input.contains(" /from ") && input.contains(" /to ")) {
                    MrIncredible.addEventTask(input.substring(6).trim());
                } else {
                    MrIncredible.handleError("The event description, start, or end time is missing or improperly formatted. Use: event <description> /from <start> /to <end>");
                }
            } else if (input.startsWith("delete ")) {
                MrIncredible.deleteTask(input);
            } else {
                MrIncredible.handleError("Sorry, I don't recognize that command. Please try again.");
            }
        }

        scanner.close();
    }

    public static void handleError(String errorMessage) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    OOPS!!! " + errorMessage);
        System.out.println("    ____________________________________________________________");
    }

    public static void deleteTask(String input) {
        try {
            int taskId = Integer.parseInt(input.substring(7).trim());
            Task removedTask = taskStorage.getTask(taskId);
            if (removedTask != null) {
                taskStorage.deleteTask(taskId);
                System.out.println("    ____________________________________________________________");
                System.out.println("    Noted. I've removed this task:");
                System.out.println("      " + removedTask);
                System.out.println("    Now you have " + taskStorage.getTaskCount() + " tasks in the list.");
                System.out.println("    ____________________________________________________________");
            } else {
                System.out.println("    Invalid task ID.");
            }
        } catch (NumberFormatException e) {
            System.out.println("    Invalid task ID.");
        }
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
