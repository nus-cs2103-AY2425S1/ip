import java.util.Scanner;

public class Elsa {
    public static void main(String[] args) {
        System.out.println("Hi! I'm Elsa");
        System.out.println("It's nice to meet you.\nHow can I help you?");
        addLine();
        processUserInput();
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
    private static void processUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        if (userInput.toLowerCase().startsWith("bye")) {
            addLine();
            goodbye();
            addLine();
            scanner.close();
        } else {
            addLine();
            System.out.println(userInput);
            addLine();
            processUserInput();
        }
    }
}
