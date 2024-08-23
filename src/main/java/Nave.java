import java.util.Scanner;

public class Nave {
    public static void main(String[] args) {
        String greeting = """
                -----------------------------------------------------------------
                Hello! :) I'm Nave, your personal task management assistant.
                What can I do for you today?
                -----------------------------------------------------------------
                """;
        String farewell = """
                -----------------------------------------------------------------
                Goodbye :( Come visit me again!
                -----------------------------------------------------------------
                """;
        System.out.println(greeting);
        Scanner inputReader = new Scanner(System.in);
        String userInput = inputReader.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println(
                    "-----------------------------------------------------------------\n"
                    + userInput +
                    "\n-----------------------------------------------------------------");
            userInput = inputReader.nextLine();
        }
        System.out.println(farewell);
    }
}
