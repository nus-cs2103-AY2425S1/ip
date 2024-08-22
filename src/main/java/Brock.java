import java.util.Scanner;

public class Brock {
    private static final String horizontalLine = "________________________________________________\n";
    private static final Task[] tasks = new Task[100];
    private static int tasksIndex = 0;

    // Helper function to display response with lines
    private static void displayResponse(String response) {
        System.out.println(Brock.horizontalLine + response + Brock.horizontalLine);
    }

    // Helper function to get target index
    // For the task to be marked/unmarked
    private static int getTargetIndex(String command) {
        char targetItemNumber = command.charAt(command.length() - 1);
        return Character.getNumericValue(targetItemNumber) - 1;
    }

    public static void main(String[] args) {
        // Create a scanner object
        // Reads from standard system input
        Scanner scanner = new Scanner(System.in);

        // Initial message
        String initialPart1 = "Hello! I'm Brock\n";
        String initialPart2 = "What can I do for you?\n";
        String initial = initialPart1 + initialPart2;
        displayResponse(initial);

        // Main loop
        while (true) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                String byeMessage = "Bye. Hope to see you again soon!\n";
                displayResponse(byeMessage);
                break;

            } else if (command.equals("list")) {
                // Use SB as it is a faster way to append strings
                StringBuilder tasksString = new StringBuilder();
                for (int i = 0; i < 100; i++) {
                    if (tasks[i] == null) {
                        break;
                    }
                    String itemNumber = i + 1 + ".";
                    tasksString.append(itemNumber)
                            .append("[")
                            .append(tasks[i].getStatusIcon())
                            .append("] ")
                            .append(tasks[i].getDescription());
                }
                displayResponse(tasksString.toString());

            } else if (command.startsWith("mark")) {
                Task targetTask = Brock.tasks[getTargetIndex(command)];
                targetTask.markAsDone();

                String responsePart1 = "Nice! I've marked this task as done:\n";
                String responsePart2 = "  [" + targetTask.getStatusIcon() + "] "
                        + targetTask.getDescription();
                String response = responsePart1 + responsePart2;
                displayResponse(response);

            } else if (command.startsWith("unmark")) {
                Task targetTask = Brock.tasks[getTargetIndex(command)];
                targetTask.markAsUndone();

                String responsePart1 = "OK, I've marked this task as not done yet:\n";
                String responsePart2 = "  [" + targetTask.getStatusIcon() + "] "
                        + targetTask.getDescription();
                String response = responsePart1 + responsePart2;
                displayResponse(response);

            } else {
                displayResponse("added: " + command + '\n');

                Task task = new Task(command + '\n');
                Brock.tasks[tasksIndex] = task;
                tasksIndex++;
            }
        }
    }
}

