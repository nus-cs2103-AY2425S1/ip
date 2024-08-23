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
            String[] inputComponents = userInput.split(" ", 2);
            String command = inputComponents[0];
            switch (command) {
                case "bye":
                    displayMessage("That's all folks! Hope to see you again soon!");
                    return;
                case "list":
                    displayTasks();
                    break;
                case "mark":
                    Task completeTask = tasks.completeTask(Integer.parseInt(inputComponents[1]));
                    displayMessage(
                            String.format("I've marked the task as complete:\n%s", completeTask));
                    break;
                case "unmark":
                    Task incompleteTask = tasks.uncompleteTask(Integer.parseInt(inputComponents[1]));
                    displayMessage(
                            String.format("Changed your mind? The task is set to incomplete:\n%s", incompleteTask));
                    break;
                case "todo":
                    String todoDescription = inputComponents[1];
                    addTask(new ToDo(todoDescription));
                    break;
                case "deadline":
                    String[] deadlineComponents = inputComponents[1].split(" /by ");
                    addTask(new Deadline(deadlineComponents[0], deadlineComponents[1]));
                    break;
                case "event":
                    String[] eventComponents = inputComponents[1].split(" /from | /to ");
                    addTask(new Event(eventComponents[0], eventComponents[1], eventComponents[2]));
                    break;
                default:
                    displayMessage("Unable to recognise command. Please try again.");
            }
        }
    }

    private static void addTask(Task t) {
        tasks.add(t);
        displayMessage(String.format("Alright, added task:%n%s to the list.%n"
                + "There are currently %d tasks in the list", t, tasks.getSize()));
    }

    private static void greetUser() {
        String greeting = String.format("Hello! I'm %s and I'm here to steal your credit card information.\n"
                + "What can I do for you?", NAME);
        displayMessage(greeting);
    }

    private static void displayTasks() {
        if (tasks.getSize() < 1) {
            displayMessage("You have no tasks at the moment.");
            return;
        }
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
