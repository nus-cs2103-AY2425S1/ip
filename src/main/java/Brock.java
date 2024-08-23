import Exceptions.BrockException;

import Tasks.Deadlines;
import Tasks.Events;
import Tasks.Task;
import Tasks.ToDos;

import java.util.Scanner;

public class Brock {
    enum Action {
        MARK,
        UNMARK
    }
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

    private static boolean isInteger(String commandWord) {
        try {
            Integer.parseInt(commandWord);
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    private static void validateStatus(String command, Action action) throws BrockException {
        String actionName = action == Action.MARK
                ? "Mark"
                : "Unmark";
        String[] commandWords = command.split(" ");
        int commandLength = commandWords.length;

        if (commandLength == 1) {
            throw new BrockException("Missing task number!\n");
        }
        if (commandLength > 2 || !isInteger(commandWords[1])) {
            throw new BrockException(actionName
                    + " command is in the form "
                    + actionName.toLowerCase()
                    + " <task-number>!\n");
        }

        int taskNumber = Integer.parseInt(commandWords[1]);
        int totalTasks = numTasks();
        if (taskNumber > totalTasks || taskNumber < 1) {
            throw new BrockException("Task number does not exist!\n");
        }
    }

    private static void handleStatus(String command, Action action) throws BrockException {
        validateStatus(command, action);

        Task targetTask = Brock.tasks[getTargetIndex(command)];
        if (action == Action.MARK) {
            targetTask.markAsDone();
            displayResponse("Nice! I've marked this task as done:\n"
                    + "  "
                    + getDetails(targetTask));
        } else {
            targetTask.markAsUndone();
            displayResponse("OK, I've marked this task as not done yet:\n"
                    + "  "
                    + getDetails(targetTask));
        }
    }

    // Helper function to create task and return the created task
    private static Task createTask(String command) throws BrockException {
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

            if (dueDate.isEmpty()) {
                throw new BrockException("Missing due date! Remember it is specified after /by!\n");
            }
            // Trim away the trailing whitespace
            task = new Deadlines(description.toString(),
                    dueDate.toString().trim());

        } else if (firstWord.equals("event")){
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

            if (startDate.isEmpty()) {
                throw new BrockException("Missing start date! Remember it is specified after /from!\n");
            }
            if (endDate.isEmpty()) {
                throw new BrockException("Missing end date! Remember it is specified after /to!\n");
            }
            // Trim away the trailing whitespace
            task = new Events(description.toString(),
                    startDate.toString(),
                    endDate.toString().trim());

        } else {
            throw new BrockException("Invalid command!\n");
        }

        if (description.isEmpty()) {
            throw new BrockException("Description is missing!\n");
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
            // Trim away leading & trailing whitespaces
            // Replace multiple whitespaces with a single one
            String command = scanner.nextLine()
                    .trim()
                    .replaceAll(" +", " ");
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
                try {
                    handleStatus(command, Action.MARK);
                } catch (BrockException e) {
                    displayResponse(e.getMessage());
                }

            } else if (command.startsWith("unmark")) {
                try {
                    handleStatus(command, Action.UNMARK);
                } catch (BrockException e) {
                    displayResponse(e.getMessage());
                }

            } else {
                Task task;
                try {
                    task = createTask(command);
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
                } catch (BrockException e) {
                    displayResponse(e.getMessage());
                }
            }
        }
    }
}

