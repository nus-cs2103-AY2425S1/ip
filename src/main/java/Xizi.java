
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Xizi {
    private static final String DIVIDER = "____________________________________________________________";
    private static final Pattern MARK_PATTERN = Pattern.compile("^mark (\\d+)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern UNMARK_PATTERN = Pattern.compile("^unmark (\\d+)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern TODO_PATTERN = Pattern.compile("^todo\\s*(.*)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern DEADLINE_PATTERN = Pattern.compile("^deadline\\s*(.*?)\\s*/by\\s*(.*?)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern EVENT_PATTERN = Pattern.compile("^event\\s*(.*?)\\s*/from\\s*(.*?)\\s*/to\\s*(.*?)$", Pattern.CASE_INSENSITIVE);


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList actions = new TaskList();


        System.out.println(DIVIDER);
        System.out.println(" Hello! I'm Xizi.");
        System.out.println(" What can I do for you?");
        System.out.println(DIVIDER);

        while (true) {
            String userInput = scanner.nextLine().trim(); // Normalize input

            Matcher markMatcher = MARK_PATTERN.matcher(userInput);
            Matcher unmarkMatcher = UNMARK_PATTERN.matcher(userInput);
            Matcher todoMatcher = TODO_PATTERN.matcher(userInput);
            Matcher deadlineMatcher = DEADLINE_PATTERN.matcher(userInput);
            Matcher eventMatcher = EVENT_PATTERN.matcher(userInput);
            try{
            if (markMatcher.matches()) {
                int taskNumber = Integer.parseInt(markMatcher.group(1));
                System.out.println(DIVIDER);
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(actions.markTask(taskNumber - 1));
                System.out.println(DIVIDER);
                continue;
            }

            if (unmarkMatcher.matches()) {
                int taskNumber = Integer.parseInt(unmarkMatcher.group(1));
                System.out.println(DIVIDER);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(actions.unmarkTask(taskNumber - 1));
                System.out.println(DIVIDER);
                continue;
            }
            if (todoMatcher.matches()) {
                String taskDescription = todoMatcher.group(1).trim();
                if (taskDescription.isEmpty()) {
                    throw new XiziException("The description of a todo cannot be empty.Type help to see the formats required.");
                }
                actions.addTask(new Todo(taskDescription));
                System.out.println(DIVIDER);
                System.out.println("Got it. I've added this task:");
                System.out.println("  [T][ ] " + taskDescription);
                System.out.println("Now you have " + actions.getSize() + " tasks in the list.");
                System.out.println(DIVIDER);
                continue;
            }

            if (deadlineMatcher.matches()) {
                String taskDescription = deadlineMatcher.group(1).trim();;
                String deadline = deadlineMatcher.group(2).trim();
                if (taskDescription.isEmpty() || deadline.isEmpty()) {
                    throw new XiziException("The description or time of a deadline cannot be empty.Type help to see the formats required.");
                }
                actions.addTask(new Deadline(taskDescription, deadline));
                System.out.println(DIVIDER);
                System.out.println("Got it. I've added this task:");
                System.out.println("  [D][ ] " + taskDescription + " (by: " + deadline + ")");
                System.out.println("Now you have " + actions.getSize() + " tasks in the list.");
                System.out.println(DIVIDER);
                continue;
            }

            if (eventMatcher.matches()) {
                String taskDescription = eventMatcher.group(1).trim();
                String fromTime = eventMatcher.group(2).trim();
                String toTime = eventMatcher.group(3).trim();
                if (taskDescription.isEmpty() || fromTime.isEmpty() || toTime.isEmpty()) {
                    throw new XiziException("The description, from or to time of an event cannot be empty.Type help to see the formats required.");
                }
                actions.addTask(new Event(taskDescription, fromTime, toTime));
                System.out.println(DIVIDER);
                System.out.println("Got it. I've added this task:");
                System.out.println("  [E][ ] " + taskDescription + " (from: " + fromTime + " to: " + toTime + ")");
                System.out.println("Now you have " + actions.getSize() + " tasks in the list.");
                System.out.println(DIVIDER);
                continue;
            }



            switch (userInput) {

                case "list":
                    System.out.println(DIVIDER);
                    System.out.println("Here are the tasks in your list:");
                    actions.printActions();
                    System.out.println(DIVIDER);
                    continue;


                case "bye":
                    System.out.println(DIVIDER);
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println(DIVIDER);
                    scanner.close();
                    return; // Exit the program
                case "help":
                    printHelp();
                    continue;
                default:
                    throw new XiziException("Sorry, I didn't understand that command. Type help for all available commands and format.");


            }} catch (XiziException e){
                printErrorMessage(e.getMessage());

            }
        }
    }
    private static void printErrorMessage(String message) {
        System.out.println(DIVIDER);
        System.out.println(" OOPS!!! " + message);
        System.out.println(DIVIDER);
    }

    private static void printHelp() {
        System.out.println(DIVIDER);
        System.out.println("Here are the available commands and their formats:");
        System.out.println();
        System.out.println("1. list");
        System.out.println("   - Displays all tasks in your list.");
        System.out.println();
        System.out.println("2. todo <task_description>");
        System.out.println("   - Adds a new 'todo' task.");
        System.out.println("     Example: todo read a book");
        System.out.println();
        System.out.println("3. deadline <task_description> /by <deadline>");
        System.out.println("   - Adds a new task with a deadline.");
        System.out.println("     Example: deadline submit report /by Monday");
        System.out.println();
        System.out.println("4. event <task_description> /from <start_time> /to <end_time>");
        System.out.println("   - Adds a new event with a start and end time.");
        System.out.println("     Example: event project meeting /from 2pm /to 4pm");
        System.out.println();
        System.out.println("5. mark <task_number>");
        System.out.println("   - Marks the specified task as done.");
        System.out.println("     Example: mark 2");
        System.out.println();
        System.out.println("6. unmark <task_number>");
        System.out.println("   - Unmarks the specified task (sets it as not done).");
        System.out.println("     Example: unmark 2");
        System.out.println();
        System.out.println("7. delete <task_number>");
        System.out.println("   - Deletes the specified task.");
        System.out.println("     Example: delete 3");
        System.out.println();
        System.out.println("8. help");
        System.out.println("   - Displays this help message with all available commands and formats.");
        System.out.println();
        System.out.println("9. bye");
        System.out.println("   - Exits the application.");
        System.out.println(DIVIDER);
    }

}
