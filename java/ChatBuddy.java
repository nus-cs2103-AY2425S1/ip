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
                if (userInput.equals("list")) { // Display all stored tasks
                    System.out.println("_____________________________________________");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + "." + tasks[i]);
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
                } else if (userInput.startsWith("todo ")) {
                    // Add a ToDo task
                    String description = userInput.substring(5);
                    tasks[taskCount] = new ToDo(description);
                    taskCount++;
                    System.out.println("_____________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + tasks[taskCount - 1]);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                    System.out.println("_____________________________________________");
                } else if (userInput.startsWith("deadline ")) {
                    // Add a Deadline task
                    String[] parts = userInput.split(" /by ");
                    String description = parts[0].substring(9);
                    String by = parts[1];
                    tasks[taskCount] = new Deadline(description, by);
                    taskCount++;
                    System.out.println("_____________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + tasks[taskCount - 1]);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                    System.out.println("_____________________________________________");
                } else if (userInput.startsWith("event ")) {
                    // Add a Event task
                    String[] parts = userInput.split(" /from | /to ");
                    String description = parts[0].substring(6);
                    String from = parts[1];
                    String to = parts[2];
                    tasks[taskCount] = new Event(description, from, to);
                    taskCount++;
                    System.out.println("_____________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + tasks[taskCount - 1]);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                    System.out.println("_____________________________________________");
                } else {
                    System.out.println("_____________________________________________");
                    System.out.println("Sorry, I don't understand that command.");
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
