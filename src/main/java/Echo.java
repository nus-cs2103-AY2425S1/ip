import java.util.Scanner;

public class Echo {
    public static void main(String[] args) {
        // Creates a scanner object
        Scanner scanner = new Scanner(System.in);

        // Greets user
        String welcomeMsg =
                "_________________________________________\n" +
                "Hello! I'm Echo!\n" +
                "What can I do for you?\n" +
                "_________________________________________\n";
        System.out.println(welcomeMsg);

        Boolean isAcceptingInput = true;
        while (isAcceptingInput) { // handles input until user says bye
            isAcceptingInput = handleInput(scanner);
        }
    }
    // Handles user input
    public static Boolean handleInput(Scanner s) {
        // Reads user input
        String userInput = s.nextLine();
        switch (userInput) {
            case "bye":
                System.out.println(
                        "_________________________________________\n" +
                        "Bye. Hope to see you again soon!\n" +
                        "_________________________________________\n");
                return false;
            default:
                System.out.println(
                        "_________________________________________\n" +
                        userInput + "\n" +
                        "_________________________________________\n");
                return true;
        }
    }
}
