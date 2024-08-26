import java.util.Scanner;


public class Bigdog {

    // Array to store tasks
    static Task[] toDoList = new Task[100];
    // Counter to keep track of the number of tasks
    static int taskCounter = 0;

    private static void editList(String str) throws BigdogException {
        if (taskCounter == 100) {
            throw new BigdogException("Your list is full!");
        }
        if (str.isEmpty()) {
            throw new BigdogException("Please give me something to add!");
        }
        if (str.startsWith("mark")) {
            if (Integer.parseInt(str.substring(5)) <= 0 || Integer.parseInt(str.substring(5)) > taskCounter) {
                throw new BigdogException("That's out of your list!");
            }
            int ind = Integer.parseInt(str.substring(5)) - 1;
            toDoList[ind].mark();
            System.out.print("Nice! I've marked this task as done:\n" + toDoList[ind] + "\n");
        } else if (str.startsWith("unmark")) {
            if (Integer.parseInt(str.substring(7)) <= 0 || Integer.parseInt(str.substring(7)) > taskCounter) {
                throw new BigdogException("That's out of your list!");
            }
            int ind = Integer.parseInt(str.substring(7)) - 1;
            toDoList[ind].unmark();
            System.out.print("OK, I've marked this task as not done yet:\n" + toDoList[ind] + "\n");
        } else {
            // Add and Echo user input
            toDoList[taskCounter] = Task.of(str);
            System.out.println("Got it. I've added this task:\n" + toDoList[taskCounter]);
            taskCounter++;
            System.out.printf("Now you have %s tasks in the list.\n", taskCounter);
        }
    }

    public static void main(String[] args) {

        // Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Welcome message
        System.out.println("Hello! I'm Bigdog!\n" + "What can I do for you?\n");

        while (true) {

            // Take in user input
            String userInput = scanner.nextLine();

            // Terminating condition
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.print("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equalsIgnoreCase("list")) {

                // Print the list
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCounter; i++) {
                    System.out.println((i + 1) + "." + toDoList[i]);
                }
            } else {
                try {
                    editList(userInput);
                } catch (BigdogException e) {
                    System.out.println(e.toString());
                }
            }
        }
        scanner.close();
    }
}
