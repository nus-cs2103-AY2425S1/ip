import java.util.Scanner;

public class LunaBot {

    // Array of strings to store list of tasks
    private final static Task[] list =  new Task[100];
    // Count to keep track of number of tasks
    private static int count = 0;
    public static void main(String[] args) {

        // Greet the user
        System.out.println("___________________________________________________________________");
        System.out.println(" Hello! I'm LunaBot");
        System.out.println(" What can I do for you?");
        System.out.println("___________________________________________________________________");

        // Create scanner for user input
        Scanner scanner = new Scanner(System.in);
        String input;

        // loop scanner to echo user input
        while (true) {
            try {
                input = scanner.nextLine();
                if (input.equals("bye")) {
                    System.out.println("___________________________________________________________________");
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println("___________________________________________________________________");
                    break;
                } else if (input.equals("list")) {
                    printTaskList();
                } else if (input.startsWith("mark ")) {
                    markTask(input);
                } else if (input.startsWith("unmark ")) {
                    unmarkTask(input);
                } else if (input.startsWith("todo ")) {
                    addTodo(input);
                } else if (input.startsWith("deadline ")) {
                    addDeadline(input);
                } else if (input.startsWith("event ")) {
                    addEvent(input);
                }
                else {
                    throw new LunaBotException("Invalid command");
                }
            }
            catch (LunaBotException e) {
                System.out.println("___________________________________________________________________");
                System.out.println(" Error: " + e.getMessage());
                System.out.println("___________________________________________________________________");
            }

        }
        scanner.close();
    }

    private static void printTaskList() {
        System.out.println("___________________________________________________________________");
        if (count == 0) {
            System.out.println("Your task list is currently empty");
        }
        else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < count; i++) {
                System.out.println(" " + (i + 1) + ". " + list[i]);
            }
        }
        System.out.println("___________________________________________________________________");
    }

    // Method to mark tasks
    private static void markTask(String input) throws LunaBotException {
        try {
            String taskNumString = input.substring(5);
            // Checks to see if user provided a number
            if (taskNumString.isEmpty()) {
                throw new LunaBotException("No task number provided");
            }
            int taskNumber = Integer.parseInt(taskNumString) - 1;
            // Checks if task number provided is valid and within range
            if (taskNumber < 0 || taskNumber >= count) {
                throw new LunaBotException("Invalid task number provided");
            }
            list[taskNumber].markAsDone();
            System.out.println("___________________________________________________________________");
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + list[taskNumber]);
            System.out.println("___________________________________________________________________");
        }
        // Checks if user inputs and int
        catch (NumberFormatException e) {
            throw new LunaBotException("Invalid task number format");
        }
    }

    // Method to unmark tasks
    private static void unmarkTask(String input) throws LunaBotException {
        try {
            String taskNumString = input.substring(7);
            // Checks to see if user provided a number
            if (taskNumString.isEmpty()) {
                throw new LunaBotException("No task number provided");
            }
            int taskNumber = Integer.parseInt(taskNumString) - 1;
            // Checks if task number provided is valid and within range
            if (taskNumber < 0 || taskNumber >= count) {
                throw new LunaBotException("Invalid task number provided");
            }
            list[taskNumber].unmarkAsDone();
            System.out.println("___________________________________________________________________");
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + list[taskNumber]);
            System.out.println("___________________________________________________________________");
        }
        // Checks if user inputs and int
        catch (NumberFormatException e) {
            throw new LunaBotException("Invalid task number format");
        }
    }

    // Method for adding ToDo
    private static void addTodo(String input) throws LunaBotException {
        String description = input.substring(5);
        // Check for empty description
        if (description.isEmpty()) {
            throw new LunaBotException("Description of task cannot be empty");
        }
        list[count] = new ToDo(description);
        count++;
        System.out.println("___________________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + list[count - 1]);
        System.out.println(" Now you have " + count + " tasks in the list");
        System.out.println("___________________________________________________________________");
    }

    private static void addDeadline(String input) throws LunaBotException {
        String[] arr = input.substring(9).split(" /by ");
        if (arr.length < 2) {
            throw new LunaBotException("Invalid deadline format");
        }
        if (arr[0].isEmpty()) {
            throw new LunaBotException("Description of task cannot be empty");
        }
        if (arr[1].isEmpty()) {
            throw new LunaBotException("Deadline of task cannot be empty");
        }
        String description = arr[0];
        String by = arr[1];
        list[count] = new Deadline(description, by);
        count++;
        System.out.println("___________________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + list[count - 1]);
        System.out.println(" Now you have " + count + " tasks in the list");
        System.out.println("___________________________________________________________________");
    }

    private static void addEvent(String input) throws LunaBotException {
        String[] arr = input.substring(6).split(" /from | /to");
        if (arr.length < 3) {
            throw new LunaBotException("Invalid event format");
        }
        if (arr[0].isEmpty()) {
            throw new LunaBotException("Description of task cannot be empty");
        }
        if (arr[1].isEmpty()) {
            throw new LunaBotException("Start time of task cannot be empty");
        }
        if (arr[2].isEmpty()) {
            throw new LunaBotException("End time of task cannot be empty");
        }
        String description = arr[0];
        String from = arr[1];
        String to = arr[2];
        list[count] = new Event(description, from, to);
        count++;
        System.out.println("___________________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + list[count - 1]);
        System.out.println(" Now you have " + count + " tasks in the list");
        System.out.println("___________________________________________________________________");
    }
}
