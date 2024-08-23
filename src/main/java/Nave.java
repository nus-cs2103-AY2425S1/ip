import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Nave {
    private static final String greeting =
            "Hello! :) I'm Nave, your personal task management assistant.\n" +
            "What can I do for you today?";

    private static final String farewell = "Goodbye :( Come visit me again";
    private static final String helpMessage = """
            /help: shows all available commands
            list: shows all tasks
            todo [name]: adds a todo with associated name
            deadline [name] /by [date]: adds a deadline with associated name and date
            event [name] /from [date] /to [date]: adds an event with associated name,
                start date and end date
            bye: ends the Nave chatbot
            """;

    private static final TaskList tasks = new TaskList();
    public static void main(String[] args) {
        //Greet User
        System.out.println(formatResponse(greeting));

        Scanner inputReader = new Scanner(System.in);

        //Get user's input
        String userInput = inputReader.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println(handleInput(userInput));
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

    //Function to handle input checking
    private static String handleInput(String input) {
        String response;

        //Regex checking for mark/unmark
        Pattern markPattern = Pattern.compile("^(mark|unmark) (\\d+)$");
        Matcher markMatcher = markPattern.matcher(input);

        //Regex checking for todos
        Pattern todoPattern = Pattern.compile("^todo ([\\w\\s]+)$");
        Matcher todoMatcher = todoPattern.matcher(input);

        //Regex checking for deadlines
        Pattern deadlinePattern =
                Pattern.compile("^deadline ([\\w\\s]+) /by ([\\w\\s]+)$");
        Matcher deadlineMatcher = deadlinePattern.matcher(input);

        //Regex checking for events
        Pattern eventPattern =
                Pattern.compile("^event ([\\w\\s]+) /from ([\\w\\s]+) /to ([\\w\\s]+)$");
        Matcher eventMatcher = eventPattern.matcher(input);

        if (input.equals("list")) {
            response = tasks.listItems();
        } else if (input.equals("/help")) {
            response = helpMessage;
        } else if (markMatcher.matches()) {
            String markOrNot = markMatcher.group(1);
            int taskNumber = Integer.parseInt(markMatcher.group(2));
            response = markOrNot.equals("mark")
                    ? tasks.markItem(taskNumber)
                    : tasks.unmarkItem(taskNumber);
        } else if (todoMatcher.matches()) {
            response = tasks.addTodo(todoMatcher.group(1));
        } else if (deadlineMatcher.matches()) {
            response = tasks.addDeadline(deadlineMatcher.group(1),
                    deadlineMatcher.group(2));
        } else if (eventMatcher.matches()) {
            response = tasks.addEvent(eventMatcher.group(1),
                    eventMatcher.group(2), eventMatcher.group(3));
        }
        else {
            response = "I don't know what you want me to do! Try using /help";
        }
        return formatResponse(response);
    }
}
