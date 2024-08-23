import java.util.Scanner;

public class Edith {
    private final String lineBreak = "\n_______________________________________________________________________\n";
    private final String greeting = "Hello! I am EDITH, your personal chatbot companion:)"
            + "\nHow may I assist you?";
    private static final String prompt = "Your next instruction: ";
    private static final String indentation = "    ";
    private final String exit = "It has been my pleasure helping you. Hope to see you again soon!";

    public Edith() {
    }

    public void greet() {
        System.out.println(lineBreak + greeting + lineBreak);
    }

    public void exit() {
        System.out.println(indentation + exit + lineBreak);
    }

    public void echo(String message) {
        System.out.println(indentation + message + lineBreak);
    }

    public static void main(String[] args) {
        Edith edith = new Edith();
        edith.greet();

        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            System.out.println(prompt);
            userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                edith.exit();
                break;
            }

            edith.echo(userInput);
        }

        scanner.close();
    }
}