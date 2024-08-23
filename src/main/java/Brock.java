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

    // Helper function to create task and return the created task
    private static Task createTask(String command) {
        String[] commandWords = command.split(" ");
        String firstWord = commandWords[0];
        int commandLength = commandWords.length;

        Task task;
        StringBuilder description = new StringBuilder();
        if (firstWord.equals("todo")) {
            for (int i = 1; i < commandLength; i++) {
                description.append(commandWords[i])
                        .append(" ");
            }
            task = new ToDos(description.toString());

        } else if (firstWord.equals("deadline")) {
            for (int i = 1; i < commandLength; i++) {
                if (commandWords[i].equals("/by")) {
                    break;
                }
                description.append(commandWords[i])
                        .append(" ");
            }
            StringBuilder dueDate = new StringBuilder();
            boolean dueDateActive = false;
            for (String word : commandWords) {
                if (dueDateActive) {
                    dueDate.append(word)
                            .append(" ");
                }
                if (word.equals("/by")) {
                    dueDateActive = true;
                }
            }
            // Need to trim away the trailing whitespace
            task = new Deadlines(description.toString(),
                    dueDate.toString().trim());

        } else {
            StringBuilder startDate = new StringBuilder();
            StringBuilder endDate = new StringBuilder();
            boolean startDateActive = false;
            boolean endDateActive = false;
            for (String word : commandWords) {
                if (word.equals("/from")) {
                    startDateActive = true;
                    continue;
                }
                if (word.equals("/to")) {
                    startDateActive = false;
                    endDateActive = true;
                    continue;
                }
                if (startDateActive) {
                    startDate.append(word)
                            .append(" ");
                }
                if (endDateActive) {
                    endDate.append(word)
                            .append(" ");
                }
            }
            for (int i = 1; i < commandLength; i++) {
                if (commandWords[i].equals("/from")) {
                    break;
                }
                description.append(commandWords[i])
                        .append(" ");
            }
            // Need to trim away the trailing whitespace
            task = new Events(description.toString(),
                    startDate.toString(),
                    endDate.toString().trim());
        }
        return task;
    }

    // Helper function to count number of tasks currently
    private static int numTasks() {
        int count = 0;
        for (int i = 0; i < 100; i++) {
            if (Brock.tasks[i] != null) {
                count += 1;
            } else {
                break;
            }
        }
        return count;
    }

    // Helper function to get task details
    private static String getDetails(Task task) {
        return "[" + task.getTaskType() + "]"
                + "[" + task.getStatusIcon() + "] "
                + task.getDescription()
                + task.getExtraInfo()
                + '\n';
    }

    public static void main(String[] args) {
        // Create a scanner object
        // Reads from standard system input
        Scanner scanner = new Scanner(System.in);

        // Initial message
        displayResponse("""
                Hello! I'm Brock
                What can I do for you?
                """);

        // Main loop
        while (true) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                displayResponse("Bye. Hope to see you again soon!\n");
                break;

            } else if (command.equals("list")) {
                // Use SB as it is a faster way to append strings
                StringBuilder tasksString = new StringBuilder();
                for (int i = 0; i < 100; i++) {
                    Task currTask = Brock.tasks[i];
                    if (currTask == null) {
                        break;
                    }
                    String itemNumber = i + 1 + ".";
                    tasksString.append(itemNumber)
                            .append(getDetails(currTask));
                }
                int totalTasks = numTasks();
                displayResponse((totalTasks == 1
                        ? "Here is the task in your list:\n"
                        : "Here are the tasks in your list:\n")
                        + tasksString);

            } else if (command.startsWith("mark")) {
                Task targetTask = Brock.tasks[getTargetIndex(command)];
                targetTask.markAsDone();
                displayResponse("Nice! I've marked this task as done:\n"
                        + "  "
                        + getDetails(targetTask));

            } else if (command.startsWith("unmark")) {
                Task targetTask = Brock.tasks[getTargetIndex(command)];
                targetTask.markAsUndone();
                displayResponse("OK, I've marked this task as not done yet:\n"
                        + "  "
                        + getDetails(targetTask));

            } else {
                Task task = createTask(command);
                Brock.tasks[tasksIndex] = task;
                tasksIndex++;

                int totalTasks = numTasks();
                displayResponse("Got it. I've added this task:\n"
                        + "  "
                        + getDetails(task)
                        + "Now you have "
                        + totalTasks
                        + (totalTasks == 1
                        ? " task in the list\n"
                        : " tasks in the list\n"));
            }
        }
    }
}

