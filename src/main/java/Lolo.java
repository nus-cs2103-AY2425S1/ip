import java.util.Scanner;

public class Lolo {
    public static void main(String[] args) {
        // Create a Scanner object to read input from the user
        Scanner scanner = new Scanner(System.in);

        // Greet the user and introduce itself
        System.out.println("Hello! I'm Lolo, your digital BFF 😊 I am always there for you:)");
        System.out.println("I can remember whatever you say and even crack a joke or two!");

        // Prompt the user for input/command
        System.out.print("So, what would you like to say? \n");
        String userCommand;

        // Start a loop that continues until the user types "bye"
        do {
            // Prompt the user for input/command
            System.out.print("You: ");
            userCommand = scanner.nextLine();

            // Call the repeatCommand function to echo the user's command
            if (!userCommand.equalsIgnoreCase("bye")) {
                repeatCommand(userCommand);
            }

        } while (!userCommand.equalsIgnoreCase("bye")); // Loop ends when user types "bye"

        // Say goodbye to the user before exiting
        System.out.println("Lolo: Goodbye! It was nice talking to you. 👋");

        // Close the scanner
        scanner.close();

    }

    public static void repeatCommand(String command) {
        String response = generateResponse(command);
        System.out.println("\u001B[34mChatBot: " + response + "\u001B[0m"); // output blue text
    }

    public static String generateResponse(String command) {
        if (command.equalsIgnoreCase("tell me a joke")) {
            return "Why don't programmers like nature? It has too many bugs! 🐛";
        } else if (command.equalsIgnoreCase("hello") || command.equalsIgnoreCase("hi")) {
            return command + " nice to meet you too!";
        } else {
            return "Hmm, interesting... \"" + command + "\", right?";
        }
    }
}
