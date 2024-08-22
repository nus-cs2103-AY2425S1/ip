import java.util.Scanner;

public class Bobby {

    /**
     * This function greets the user.
     */
    private static void greet() {
        String greeting = "Hello I'm Bobby\n"
                + "What can I do for you today?";
        System.out.println(greeting);
    }

    /**
     * This function exits the chat app with a default message.
     */
    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * This function takes in the user input and prints out the input.
     * @param String input
     */
    private static void echo(String input) {
        System.out.println(input);
    }

    private static String[] tasks = new String[100];
    private static int count = 0;

    private static void add_task(String task) {
        if (count < tasks.length) {
            tasks[count] = task;
            count++;
            System.out.println("added: " + task);
        } else {
            System.out.println("Task list is full! Cannot add more tasks.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greet();
        while (true) {
            // Ask the user for input
            System.out.print("Enter something (type 'bye' to quit): ");
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                exit();
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                if (count == 0) {
                    System.out.println("No tasks added to the list yet.");
                } else {
                    for (int i = 0; i < count; i++) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                }
            } else {
                add_task(userInput);
            }
        }


    }
}
