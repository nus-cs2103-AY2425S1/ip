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

        // Create scanner for user input
        Scanner scanner = new Scanner(System.in);
        String input;

        // loop scanner to echo user input
        while (true) {
            input = scanner.nextLine();

            // exit if input is "bye"
            if (input.equals("bye")) {
                System.out.println("___________________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("___________________________________________________________________");
                break;
            }

            // List all actions when prompted
            else if (input.equals("list")) {
                System.out.println("___________________________________________________________________");
                System.out.println(" Here are the tasks in your list:");

                // loop for all tasks in the list
                for (int i = 0; i < count; i++) {
                    System.out.println(" " + (i + 1) + ". " + list[i]);
                }
                System.out.println("___________________________________________________________________");

            }
            else if (input.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(input.substring(5)) - 1;
                list[taskNumber].markAsDone();
                System.out.println("___________________________________________________________________");
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   " + list[taskNumber]);
                System.out.println("___________________________________________________________________");
            }
            else if (input.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(input.substring(7)) - 1;
                list[taskNumber].unmarkAsDone();
                System.out.println("___________________________________________________________________");
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   " + list[taskNumber]);
                System.out.println("___________________________________________________________________");
            }
            else {
                list[count] = new Task(input); // store input in the array
                count++; // increase count to index tasks and track total number of tasks
                System.out.println("___________________________________________________________________");
                System.out.println(" added: " + input);
                System.out.println("___________________________________________________________________");
            }

        }
        scanner.close();
    }
}
