import java.util.Scanner;

/**
 * This is a chatbot class named Bob.
 */
public class Bob {

    public static void main(String[] args) {
        String welcome = "Hello! I'm Bob\n"
                + "\tWhat can I do for you?";
        Bob.printLines(welcome);
        Bob bob = new Bob();
        bob.chat();
    }

    /**
     * This is a chat function by Bob.
     */
    void chat() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        while (!input.equals("bye")) {
            Bob.printLines(input);
            input = scanner.next();
        }
        printLines("Bye. Hope to see you again soon!bye");
    }

    /**
     * Prints the text input with lines above and below it.
     * @param text The user input into the chatbot
     */
    static void printLines(String text) {
        String textToPrint = "\t____________________________________________________________\n"
                + "\t"
                + text
                + "\n"
                + "\t____________________________________________________________\n";
        System.out.println(textToPrint);
    }
}
