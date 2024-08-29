import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents Elsa, a chatbot.
 * @author Aaron
 */
public class Elsa {
    private static final List<Task> THINGS_TO_DO = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hi! I'm Elsa");
        System.out.println("It's nice to meet you.\nHow can I help you?");
        addLine();
        processUserInput(THINGS_TO_DO);
    }

    /**
     * This method adds a horizontal separator line consisting of underscores.
     */
    private static void addLine() {
        System.out.println("______________________________________");
    }

    /**
     * This method ends the conversation and says bye to the user.
     */
    private static void goodbye() {
        System.out.println("Bye! It was so nice chatting with you.\nSee you again soon!");
    }

    /**
     * This method creates a new scanner object to process user input.
     */
    private static void processUserInput(List<Task> tasks) {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        if (userInput.toLowerCase().contains("bye")) {
            addLine();
            goodbye();
            addLine();
            scanner.close();
        } else if (userInput.toLowerCase().contains("list")) {
            addLine();
            System.out.println("Here are the tasks in your list:");
            int i = 1;
            for (Task task : tasks) {
                System.out.println(i + "." + task.toString());
                i++;
            }
            addLine();
            // Continue to process any additional userInput
            processUserInput(tasks);
        } else if (userInput.toLowerCase().startsWith("mark")) {
            // marks the task the user is referring to as done
            int taskNum = Character.getNumericValue(userInput.charAt(5));
            tasks.get(taskNum - 1).done();

            // Informs the user that the task has been marked as done
            addLine();
            System.out.println("Great! I've marked it as done:\n  " + tasks.get(taskNum - 1).toString());
            addLine();

            // Continue to process any additional userInput
            processUserInput(tasks);
        } else if (userInput.toLowerCase().startsWith("unmark")) {
            // marks the task the user is referring to as not done
            int taskNum = Character.getNumericValue(userInput.charAt(7));
            tasks.get(taskNum - 1).notDone();

            // Informs the user that the task has been marked as not done
            addLine();
            System.out.println("Alright, I've unchecked this task:\n  " + tasks.get(taskNum - 1).toString());
            addLine();

            // Continue to process any additional userInput
            processUserInput(tasks);
        } else {
            // Add the userInput to the thingsToDo list
            Task newTask = new Task(userInput);
            THINGS_TO_DO.add(newTask);

            // Inform the user that the task has been added
            addLine();
            System.out.println("added: " + userInput);
            addLine();

            // Continue to process any additional userInput
            processUserInput(tasks);
        }
    }
}
