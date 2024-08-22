import java.util.Scanner;

public class Yapper {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100]; // Array to store tasks
        int taskCount = 0; // Counter to keep track of the number of tasks

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Yapper");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (userInput.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else {
                tasks[taskCount] = userInput;
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println(" added: " + userInput);
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }
}
