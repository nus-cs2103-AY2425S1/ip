import java.util.Scanner;

public class MrIncredible {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TaskStorage taskStorage = new TaskStorage();
    public static void main(String[] args) {

        MrIncredible.greet();
        // Start an infinite loop to handle user input
        while (true) {
            // Read user input
            String input = scanner.nextLine();

            // Exit condition
            if (input.equals("bye")) {
                MrIncredible.sayBye();
                break;
            } else if (input.equals("list")) {
                //Handle list command
                MrIncredible.listTasks();
            } else if (input.length() >= 4 && input.substring(0,4).equals("mark")) {
                MrIncredible.markTask(input);
            }
            else { // Handle other inputs as task
                MrIncredible.addTask(input);
            }
        }

        // Close the scanner
        scanner.close();
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
