import java.util.ArrayList;
import java.util.Scanner;
public class Yap {
    public static void main(String[] args) {
        // Initialize Scanner
        Scanner scanner = new Scanner(System.in);

        // Print logo and introductions
//        String logo = "_    _  __     ______\n"
//                    + " \\  // //\\    ||__| |\n"
//                    + "  \\// //__\\   ||____/\n"
//                    + "  || //____\\  ||\n"
//                    + "  ||//      \\ ||\n";
        String logo = "Yap";
        String separator = "_____________________________________";
        System.out.println("Hello from " + logo);
        System.out.println("What would you like me to do for you?");
        System.out.println(separator);

        // Infinite loop to get user input
        Storage storage = new Storage("yap.txt", "./data/");
        TaskList taskList = new TaskList(storage);

        while (true) {
            String userInput = scanner.nextLine();

            // Bye functionality
            try {
                if (userInput.equalsIgnoreCase("bye")) {
                    System.out.println("Bye! It was really nice talking to you, see you soon :)");
                    break;
                }

                // Mark functionality
                if (userInput.startsWith("mark")) {
                    try {
                        int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                        taskList.markTask(taskIndex, storage);
                        System.out.println(separator);
                    } catch (IndexOutOfBoundsException | NumberFormatException exception) {
                        throw new InputException("You did not provide a valid task index to mark!");
                    }
                    continue;
                }

                // Unmark functionality
                if (userInput.startsWith("unmark")) {
                    try {
                        int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                        taskList.unmarkTask(taskIndex, storage);
                        System.out.println(separator);
                    } catch (IndexOutOfBoundsException | NumberFormatException exception) {
                        throw new InputException("You did not provide a valid task index to mark!");
                    }
                    continue;
                }

                // Delete functionality
                if (userInput.startsWith("delete")) {
                    try {
                        int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                        taskList.deleteTask(taskIndex, storage);
                        System.out.println(separator);
                    } catch (IndexOutOfBoundsException | NumberFormatException exception) {
                        throw new InputException("You did not provide a valid task index to mark!");
                    }
                    continue;
                }

                // If user adds a todo task
                if (userInput.startsWith("todo")) {
                    Task task = InputParser.parseInputAsToDo(userInput);
                    taskList.addTask(task, storage);
                    System.out.println(separator);
                    continue;
                }

                // If user adds a deadline task
                if (userInput.startsWith("deadline")) {
                    Task task = InputParser.parseInputAsDeadline(userInput);
                    taskList.addTask(task, storage);
                    System.out.println(separator);
                    continue;
                }

                // If user adds an event task
                if (userInput.startsWith("event")) {
                    Task task = InputParser.parseInputAsEvent(userInput);
                    taskList.addTask(task, storage);
                    System.out.println(separator);
                    continue;
                }

                // List functionality
                if (userInput.startsWith("list")) {
                    taskList.listTasks();
                    System.out.println(separator);
                    continue;
                }
                throw new InputException();
            } catch (InputException error) {
                System.out.println(error.getMessage());
                System.out.println(separator);
            }
        }
    }
}
