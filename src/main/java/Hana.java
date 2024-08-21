import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hana {
    public static void main(String[] args) {
        String line = "    ____________________________________________________________\n";
        String greeting = "    Hello I'm Hana\n" +
                "    What can I do for you?\n";
        String closing = "    Bye. Hope to see you again soon!\n";
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        Task[] tasks = new Task[100];
        int numberOfTasks = 0;
        // Regular expressions for mark and unmark commands
        Pattern markPattern = Pattern.compile("^mark (\\d+)$");
        Pattern unmarkPattern = Pattern.compile("^unmark (\\d+)$");

        System.out.println(line + greeting + line);
        String input = scanner.nextLine();  // Read user input
        String output = "";
        while (!input.equals("bye")) {
            // Check for mark/unmark in input
            Matcher markMatcher = markPattern.matcher(input);
            Matcher unmarkMatcher = unmarkPattern.matcher(input);
            if (input.equals("list")) {
                System.out.println(line);
                for (int i = 0; i < numberOfTasks; i++) {
                    System.out.println("    " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println(line);
            } else if (markMatcher.matches()) {
                // taskNumber is index already
                int taskNumber = Integer.parseInt(markMatcher.group(1)) - 1;
                if (taskNumber >= 0 && taskNumber < numberOfTasks) {
                    tasks[taskNumber].markAsDone();
                    System.out.println(line + "     Nice! I've marked this task as done:");
                    System.out.println("    " + tasks[taskNumber] + "\n" + line);
                } else {
                    System.out.println(line + "     Task number out of range.\n" + line);
                }
            } else if (unmarkMatcher.matches()) {
                // taskNumber is index already
                int taskNumber = Integer.parseInt(unmarkMatcher.group(1)) - 1;
                if (taskNumber >= 0 && taskNumber < numberOfTasks) {
                    tasks[taskNumber].markAsUndone();
                    System.out.println(line + "     OK, I've marked this task as not done yet:");
                    System.out.println("    " + tasks[taskNumber] + "\n" + line);
                } else {
                    System.out.println(line + "     Task number out of range.\n" + line);
                }
            }
            else {
                Task task = new Task(input);
                tasks[numberOfTasks] = task;
                numberOfTasks++;
                output = "    added: " + input + "\n";
                System.out.println(line + output + line);
            }
            input = scanner.nextLine();
        }
        System.out.println(line + closing + line);
    }
}
