
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
            String userInput = scanner.nextLine().trim().toLowerCase(); // Normalize input
            Pattern markPattern = Pattern.compile("^mark (\\d+)$", Pattern.CASE_INSENSITIVE);
            Matcher markMatcher = markPattern.matcher(userInput);

            Pattern unmarkPattern = Pattern.compile("^unmark (\\d+)$", Pattern.CASE_INSENSITIVE);
            Matcher unmarkMatcher = unmarkPattern.matcher(userInput);

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
                    System.out.println("____________________________________________________________");
                    System.out.println("added: " + userInput);
                    System.out.println("____________________________________________________________");
                    actions.addTask(userInput);
                    break;
            }
        }
    }
}
