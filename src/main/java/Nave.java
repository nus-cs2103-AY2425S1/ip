import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Nave {
    private static final String greeting =
            "Hello! :) I'm Nave, your personal task management assistant.\n" +
            "What can I do for you today?";

    private static final String farewell = "Goodbye :( Come visit me again";

    private static final TaskList tasks = new TaskList();
    public static void main(String[] args) {
        //Greet User
        System.out.println(formatResponse(greeting));

        Scanner inputReader = new Scanner(System.in);
        Pattern pattern = Pattern.compile("^(mark|unmark) (\\d+)$");
        //Get user's input
        String userInput = inputReader.nextLine();
        while (!userInput.equals("bye")) {
            Matcher matcher = pattern.matcher(userInput);
            if (userInput.equals("list")) {
                System.out.println(formatResponse(tasks.listItems()));
            } else if (matcher.matches()) {
                String markOrNot = matcher.group(1);
                int taskNumber = Integer.parseInt(matcher.group(2));
                String response = markOrNot.equals("mark")
                        ? tasks.markItem(taskNumber)
                        : tasks.unmarkItem(taskNumber);
                System.out.println(formatResponse(response));
            } else {
                System.out.println(formatResponse(tasks.addItem(userInput)));
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
