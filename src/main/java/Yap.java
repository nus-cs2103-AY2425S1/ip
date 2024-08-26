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
        ArrayList<Task> tasks = new ArrayList<>();
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
                        if (tasks.get(taskIndex) == null) {
                            throw new IndexOutOfBoundsException();
                        }
                        tasks.get(taskIndex).markAsDone();
                        System.out.println("I've marked this task as done:");
                        System.out.println(tasks.get(taskIndex));
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
                        tasks.get(taskIndex).markAsUndone();
                        System.out.println("I've marked this task as not done:");
                        System.out.println(tasks.get(taskIndex));
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
                        Task tempTask = tasks.get(taskIndex);
                        tasks.remove(taskIndex);
                        System.out.println("I've removed this task:");
                        System.out.println(tempTask);
                        System.out.println(separator);
                    } catch (IndexOutOfBoundsException | NumberFormatException exception) {
                        throw new InputException("You did not provide a valid task index to mark!");
                    }
                    continue;
                }

                // If user adds a todo task
                if (userInput.startsWith("todo")) {
                    tasks.add(InputParser.parseInputAsToDo(userInput));
                    System.out.println("Added: " + tasks.get(tasks.size() - 1));
                    System.out.printf("You now have %d tasks in the list%n", tasks.size());
                    System.out.println(separator);
                    continue;
                }

                // If user adds a deadline task
                if (userInput.startsWith("deadline")) {
                    tasks.add(InputParser.parseInputAsDeadline(userInput));
                    System.out.println("Added: " + tasks.get(tasks.size() - 1));
                    System.out.printf("You now have %d tasks in the list%n", tasks.size());
                    System.out.println(separator);
                    continue;
                }

                // If user adds an event task
                if (userInput.startsWith("event")) {
                    tasks.add(InputParser.parseInputAsEvent(userInput));
                    System.out.println("Added: " + tasks.get(tasks.size() - 1));
                    System.out.printf("You now have %d tasks in the list%n", tasks.size());
                    System.out.println(separator);
                    continue;
                }

                // List functionality
                if (userInput.startsWith("list")) {
                    for (int input = 0; input < tasks.size(); ++input) {
                        System.out.println((input + 1) + ". " + tasks.get(input).toString());
                    }
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
