import java.util.ArrayList;
import java.util.Scanner;

public class LBot {
    public static void main(String[] args) {
        String greeting = "Hello! I'm LBot, your dedicated personal assistant ;)\nWhat can I do for you?";
        String exitMsg = "Bye. Hope to smell you again!";
        // Initialise Scanner object
        Scanner scanner = new Scanner(System.in);
        System.out.println(greeting);
        String userInput = "";
        ArrayList<Task> taskList = new ArrayList<Task>();
        while (true) {
            userInput = scanner.nextLine();
            // current assumption: each input has a command
            /*
                Commands:
                1. Add task
                2. List tasks
             */
            String command = userInput.split("\\s+")[0];
            switch (command) {
                case "bye":
                    System.out.println(exitMsg);
                    scanner.close();
                    System.exit(0);
                    break;
                case "add":
                    // get details of task, removes command
                    String taskName = userInput.substring(command.length() + 1);
                    try {
                        taskList.add(new Task(taskName));
                        System.out.println("Successfully added task: " + taskList.get(taskList.size() - 1));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "list":
                    for (int i = 1; i < taskList.size() + 1; i++) {
                        System.out.println(i + ": " + taskList.get(i - 1).toString());
                    }
                    break;
                default:
                    System.out.println("Unknown command. Please check your input.");
            }
        }
    }
}
