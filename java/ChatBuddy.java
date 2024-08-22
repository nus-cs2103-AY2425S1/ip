import java.util.Scanner;

public class ChatBuddy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100]; // Array to store up to 100 tasks
        int taskCount = 0; // To keep track of the number of tasks stored

        try {
            System.out.println("_____________________________________________");
            System.out.println("Hello! I'm ChatBuddy.\nLet me know how can I assist you today?");
            System.out.println("_____________________________________________");

            String userInput = scanner.nextLine();
            while (!userInput.equals("bye")) {
                if (userInput.equals("list")) {
                    System.out.println("_____________________________________________");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                    System.out.println("_____________________________________________");
                } else if (userInput.startsWith("mark ")) {
                    // Mark a task as done
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    if (taskNumber < taskCount && taskNumber >= 0) {
                        tasks[taskNumber].markAsDone();
                        System.out.println("_____________________________________________");
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(" " + tasks[taskNumber]);
                        System.out.println("_____________________________________________");
                    }
                } else if (userInput.startsWith("unmark ")){
                    // Unmark a task as not done
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    if (taskNumber < taskCount && taskNumber >= 0) {
                        tasks[taskNumber].unmarkAsDone();
                        System.out.println("_____________________________________________");
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(" " + tasks[taskNumber]);
                        System.out.println("_____________________________________________");
                    }
                } else {
                    // Store the user input as a new task
                    tasks[taskCount] = new Task(userInput);
                    taskCount++;
                    System.out.println("_____________________________________________");
                    System.out.println("Added: " + userInput);
                    System.out.println("_____________________________________________");
                }
                userInput = scanner.nextLine();
            }
        } finally {
            System.out.println("_____________________________________________");
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println("_____________________________________________");
        }
    }
}
