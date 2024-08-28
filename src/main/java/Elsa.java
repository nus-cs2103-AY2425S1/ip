import java.util.Scanner;

public class Elsa {
    public static void main(String[] args) {
        System.out.println("Hi! I'm Elsa");
        System.out.println("It's nice to meet you.\nHow can I help you?");
        addLine();
        String[] thingsToDo = new String[100];
        processUserInput(thingsToDo);
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
    private static void processUserInput(String[] tasks) {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        if (userInput.toLowerCase().startsWith("bye")) {
            addLine();
            goodbye();
            addLine();
            scanner.close();
        } else if (userInput.toLowerCase().startsWith("list")) {
            addLine();
            int i = 0;
            while (tasks[i] != null) {
                System.out.println(tasks[i]);
                i++;
            }
            addLine();
            // Continue to process any additional userInput
            processUserInput(tasks);
        } else {
            // Add the userInput to the thingsToDo array
            int i = 0;
            while (tasks[i] != null) {
                i++;
            }
            tasks[i] = i + 1 + ". " + userInput;

            // Inform the user that the task has been added
            addLine();
            System.out.println("added: " + userInput);
            addLine();

            // Continue to process any additional userInput
            processUserInput(tasks);
        }
    }
}
