import java.util.Scanner;

public class Nave {
    private static final String greeting =
            "Hello! :) I'm Nave, your personal task management assistant.\n" +
            "What can I do for you today?";

    private static final String farewell = "Goodbye :( Come visit me again";
    public static void main(String[] args) {
        //Greet User
        System.out.println(formatResponse(greeting));

        Scanner inputReader = new Scanner(System.in);
        //Get user's input
        String userInput = inputReader.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println(formatResponse(List.listItems()));
            } else {
                System.out.println(formatResponse(List.addItem(userInput)));
            }
            userInput = inputReader.nextLine();
        }
        //Say goodbye
        System.out.println(formatResponse(farewell));
    }

    private static String formatResponse(String input) {
        return "-----------------------------------------------------------------\n"
                + input + "\n"
                + "-----------------------------------------------------------------";
    }
}
