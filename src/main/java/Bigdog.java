import java.util.Scanner;


public class Bigdog {

    // Array to store tasks
    static Task[] toDoList = new Task[100];
    // Counter to keep track of the number of tasks
    static int taskCounter = 0;

    private static void editList(String str) {
        if (str.startsWith("mark")) {
            int ind = str.charAt(str.length() - 1) - '1';
            toDoList[ind].mark();
            System.out.print("Nice! I've marked this task as done:\n" + toDoList[ind] + "\n");
        } else if (str.startsWith("unmark")){
            int ind = str.charAt(str.length() - 1) - '1';
            toDoList[ind].unmark();
            System.out.print("OK, I've marked this task as not done yet:\n" + toDoList[ind] + "\n");
        } else {
            // Add and Echo user input
            toDoList[taskCounter] = Task.of(str);
            System.out.println("added: " + toDoList[taskCounter]);
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
                editList(userInput);
            }
        }
        scanner.close();
    }
}
