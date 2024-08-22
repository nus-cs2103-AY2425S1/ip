import java.util.Scanner;
public class Bigmouth {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        // Introduction
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Bigmouth\nWhat can I do for you?\n");
        System.out.println("____________________________________________________________");

        // Main loop to handle user input
        while (true) {
            String userInput = scanner.nextLine();

            // Exit the program if the user types "bye"
            if (userInput.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }

            // List all stored tasks if the user types "list"
            if (userInput.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + "." + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            }
            // Mark a task as done
            else if (userInput.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks[taskNumber].markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   " + tasks[taskNumber]);
                System.out.println("____________________________________________________________");
            }
            // Unmark a task as not done
            else if (userInput.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks[taskNumber].markAsNotDone();
                System.out.println("____________________________________________________________");
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   " + tasks[taskNumber]);
                System.out.println("____________________________________________________________");
            }
            // Store a new task
            else {
                tasks[taskCount] = new Task(userInput);
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println(" added: " + userInput);
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }
}
