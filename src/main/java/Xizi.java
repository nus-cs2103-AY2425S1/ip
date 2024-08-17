
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Xizi {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList actions = new TaskList();

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Xizi");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String userInput = scanner.nextLine().trim(); // Normalize input
            Pattern markPattern = Pattern.compile("^mark (\\d+)$", Pattern.CASE_INSENSITIVE);
            Matcher markMatcher = markPattern.matcher(userInput);

            Pattern unmarkPattern = Pattern.compile("^unmark (\\d+)$", Pattern.CASE_INSENSITIVE);
            Matcher unmarkMatcher = unmarkPattern.matcher(userInput);

            Pattern todoPattern = Pattern.compile("^todo (.+)$", Pattern.CASE_INSENSITIVE);
            Pattern deadlinePattern = Pattern.compile("^deadline (.+) /by (.+)$", Pattern.CASE_INSENSITIVE);
            Pattern eventPattern = Pattern.compile("^event (.+) /from (.+) /to (.+)$", Pattern.CASE_INSENSITIVE);
            Matcher todoMatcher = todoPattern.matcher(userInput);
            Matcher deadlineMatcher = deadlinePattern.matcher(userInput);
            Matcher eventMatcher = eventPattern.matcher(userInput);

            if (markMatcher.matches()) {
                int taskNumber = Integer.parseInt(markMatcher.group(1));
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(actions.markTask(taskNumber - 1));
                System.out.println("____________________________________________________________");
                continue;
            }

            if (unmarkMatcher.matches()) {
                int taskNumber = Integer.parseInt(unmarkMatcher.group(1));
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(actions.unmarkTask(taskNumber - 1));
                System.out.println("____________________________________________________________");
                continue;
            }
            if (todoMatcher.matches()) {
                String taskDescription = todoMatcher.group(1);
                actions.addTask(new Todo(taskDescription));
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println("  [T][ ] " + taskDescription);
                System.out.println("Now you have " + actions.getSize() + " tasks in the list.");
                System.out.println("____________________________________________________________");
                continue;
            }

            if (deadlineMatcher.matches()) {
                String taskDescription = deadlineMatcher.group(1);
                String deadline = deadlineMatcher.group(2);
                actions.addTask(new Deadline(taskDescription, deadline));
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println("  [D][ ] " + taskDescription + " (by: " + deadline + ")");
                System.out.println("Now you have " + actions.getSize() + " tasks in the list.");
                System.out.println("____________________________________________________________");
                continue;
            }

            if (eventMatcher.matches()) {
                String taskDescription = eventMatcher.group(1);
                String fromTime = eventMatcher.group(2);
                String toTime = eventMatcher.group(3);
                actions.addTask(new Event(taskDescription, fromTime, toTime));
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println("  [E][ ] " + taskDescription + " (from: " + fromTime + " to: " + toTime + ")");
                System.out.println("Now you have " + actions.getSize() + " tasks in the list.");
                System.out.println("____________________________________________________________");
                continue;
            }


            switch (userInput) {

                case "list":
                    System.out.println("____________________________________________________________");
                    System.out.println("Here are the tasks in your list:");
                    actions.printActions();
                    System.out.println("____________________________________________________________");
                    continue;


                case "bye":
                    System.out.println("____________________________________________________________");
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    scanner.close();
                    return; // Exit the program

                default:

                    break;
            }
        }
    }
}
