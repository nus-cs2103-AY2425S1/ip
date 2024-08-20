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
                3. Mark tasks as complete
             */
            String command = userInput.split("\\s+")[0];
            switch (command) {
                case "bye":
                    System.out.println(exitMsg);
                    scanner.close();
                    System.exit(0);
                    break;
                case "add":
                    try {
                        // get details of task, removes command
                        String taskName = userInput.substring(command.length() + 1);
                        taskList.add(new Task(taskName));
                        System.out.println("Successfully added task: " + taskName);
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Please specify task name.");
                    }
                    break;
                case "list":
                    if (taskList.isEmpty()) {
                        System.out.println("No tasks found");
                        break;
                    }
                    System.out.println("Task List:");
                    for (int i = 1; i < taskList.size() + 1; i++) {
                        System.out.println("\t" + i + ": " + taskList.get(i - 1).toString());
                    }
                    break;
                case "mark":
                    try {
                        int taskNo = Integer.parseInt(userInput.substring(command.length() + 1)) - 1;
                        taskList.get(taskNo).setComplete(true);
                        System.out.println("Successfully marked task: " + taskList.get(taskNo));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Task does not exist");
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Specify a task to mark.");
                    }
                    break;
                default:
                    System.out.println("Unknown command. Please check your input.");
            }
        }
    }
}
