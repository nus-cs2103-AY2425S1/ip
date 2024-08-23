import java.util.ArrayList;
import java.util.Scanner;

public class Lawrence {
    private static final String NAME = "Lawrence";
    private static final Tasks tasks = new Tasks();

    public static void main(String[] args) {
        greetUser();
        Scanner sc = new Scanner(System.in);

        String userInput;
        while (true) {
            userInput = sc.nextLine();  // Get next user input
            String[] components = userInput.split(" ");
            switch (components[0]) {
                case "bye":
                    displayMessage("That's all folks! Hope to see you again soon!");
                    return;
                case "list":
                    displayTasks();
                    break;
                case "mark":
                    Task completeTask = tasks.completeTask(Integer.parseInt(components[1]));
                    displayMessage(
                            String.format("I've marked the task as complete:\n%s", completeTask));
                    break;
                case "unmark":
                    Task incompleteTask = tasks.unCompleteTask(Integer.parseInt(components[1]));
                    displayMessage(
                            String.format("Changed your mind? The task is set to incomplete:\n%s", incompleteTask));
                    break;
                default:
                    Task t = new Task(userInput);
                    tasks.addTask(t);
                    displayMessage(String.format("Alright, added task: %s to the list.", t));
            }
        }
    }

    private static void greetUser() {
        String greeting = String.format("Hello! I'm %s and I'm here to steal your credit card information.\n"
                + "What can I do for you?", NAME);
        displayMessage(greeting);
    }

    private static void displayTasks() {
        displayMessage(String.format("Here's your laundry list:%n%s", tasks.toString()));
    }

    private static void displayMessage(String message) {
        displayHorizontalLine();
        System.out.println(message);
        displayHorizontalLine();
    }

    private static void displayHorizontalLine() {
        System.out.println("____________________");
    }
}
