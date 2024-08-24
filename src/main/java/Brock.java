import Exceptions.BrockException;

import Tasks.Deadlines;
import Tasks.Events;
import Tasks.Task;
import Tasks.ToDos;

import java.util.ArrayList;
import java.util.Scanner;

public class Brock {
    enum Action {
        MARK,
        UNMARK
    }
    private static final String horizontalLine = "________________________________________________\n";
    private static final ArrayList<Task> tasks = new ArrayList<>();

    // Helper function to display response with lines
    private static void displayResponse(String response) {
        System.out.println(Brock.horizontalLine
                + response
                + Brock.horizontalLine);
    }

    // Helper function to get target index
    // For the task to be marked/unmarked
    private static int getTargetIndex(String command) {
        char targetItemNumber = command.charAt(command.length() - 1);
        return Character.getNumericValue(targetItemNumber) - 1;
    }

    // Helper function to process if string reflects an integer value
    private static boolean isNotInteger(String commandWord) {
        try {
            Integer.parseInt(commandWord);
        } catch(NumberFormatException | NullPointerException e) {
            return true;
        }
        return false;
    }

    // Helper function to count number of tasks currently
    private static int numTasks() {
        int count = 0;
        for (int i = 0; i < Brock.tasks.size(); i++) {
            if (Brock.tasks.get(i) != null) {
                count += 1;
            } else {
                break;
            }
        }
        return count;
    }

    // Helper function to get task details as a string
    private static String getDetails(Task task) {
        return "[" + task.getTaskType() + "]"
                + "[" + task.getStatusIcon() + "] "
                + task.getDescription()
                + task.getExtraInfo()
                + '\n';
    }

    // Helper function to get task summary, detailing current number of tasks
    private static String getTaskSummary() {
        int totalTasks = numTasks();
        return "Now you have "
                + totalTasks
                + (totalTasks == 1
                ? " task in the list.\n"
                : " tasks in the list.\n");
    }

    // Helper function to perform list command
    private static void handleList() {
        // Use SB as it is a faster way to append strings
        StringBuilder tasksString = new StringBuilder();
        for (int i = 0; i < Brock.tasks.size(); i++) {
            Task currTask = Brock.tasks.get(i);
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
    }

    // Helper function to validate the status (mark/unmark) command
    private static void validateStatus(String command, Action action) throws BrockException {
        String actionName = action == Action.MARK
                ? "Mark"
                : "Unmark";
        String[] commandWords = command.split(" ");
        int commandLength = commandWords.length;

        if (commandLength == 1) {
            throw new BrockException("Missing task number!\n");
        }
        if (commandLength > 2 || isNotInteger(commandWords[1])) {
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

    // Helper function to validate & perform the status command (if correct)
    private static void handleStatus(String command, Action action) throws BrockException {
        validateStatus(command, action);

        int targetIndex = getTargetIndex(command);
        Task targetTask = Brock.tasks.get(targetIndex);
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

    // Helper function to validate the delete command
    private static void validateDelete(String command) throws BrockException {
        String[] commandWords = command.split(" ");
        int commandLength = commandWords.length;

        if (commandLength == 1) {
            throw new BrockException("Missing task number!\n");
        }
        if (commandLength > 2 || isNotInteger(commandWords[1])) {
            throw new BrockException("Delete command is in the form delete <task-number>!\n");
        }

        int taskNumber = Integer.parseInt(commandWords[1]);
        int totalTasks = numTasks();
        if (taskNumber > totalTasks || taskNumber < 1) {
            throw new BrockException("Task number does not exist!\n");
        }

    }

    // Helper function to validate & perform the delete command (if correct)
    private static void handleDelete(String command) throws BrockException {
        validateDelete(command);

        int targetIndex = getTargetIndex(command);
        Task removedTask = Brock.tasks.get(targetIndex);
        Brock.tasks.remove(targetIndex);
        displayResponse("Noted. I've removed this task:\n"
                + "  "
                + getDetails(removedTask)
                + getTaskSummary());
    }

    // Helper function to create task and return the created task
    private static Task createTask(String command) throws BrockException {
        String[] commandWords = command.split(" ");
        String firstWord = commandWords[0];
        int commandLength = commandWords.length;

        Task task;
        StringBuilder description = new StringBuilder();
        if (firstWord.equalsIgnoreCase("todo")) {
            for (int i = 1; i < commandLength; i++) {
                description.append(commandWords[i])
                        .append(" ");
            }
            task = new ToDos(description.toString());

        } else if (firstWord.equalsIgnoreCase("deadline")) {
            for (int i = 1; i < commandLength; i++) {
                if (commandWords[i].equalsIgnoreCase("/by")) {
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
                if (word.equalsIgnoreCase("/by")) {
                    dueDateActive = true;
                }
            }

            if (dueDate.isEmpty()) {
                throw new BrockException("Missing due date! Remember it is specified after /by!\n");
            }
            // Trim away the trailing whitespace
            task = new Deadlines(description.toString(),
                    dueDate.toString().trim());

        } else if (firstWord.equalsIgnoreCase("event")){
            StringBuilder startDate = new StringBuilder();
            StringBuilder endDate = new StringBuilder();
            boolean startDateActive = false;
            boolean endDateActive = false;
            for (String word : commandWords) {
                if (word.equalsIgnoreCase("/from")) {
                    startDateActive = true;
                    continue;
                }
                if (word.equalsIgnoreCase("/to")) {
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
                if (commandWords[i].equalsIgnoreCase("/from")) {
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
            if (command.equalsIgnoreCase("bye")) {
                displayResponse("Bye. Hope to see you again soon!\n");
                break;

            } else if (command.equalsIgnoreCase("list")) {
                handleList();

            // Case-insensitive, convert to lower case then process
            } else if (command.toLowerCase().startsWith("mark")) {
                try {
                    handleStatus(command, Action.MARK);
                } catch (BrockException e) {
                    displayResponse(e.getMessage());
                }

            } else if (command.toLowerCase().startsWith("unmark")) {
                try {
                    handleStatus(command, Action.UNMARK);
                } catch (BrockException e) {
                    displayResponse(e.getMessage());
                }

            } else if (command.toLowerCase().startsWith("delete")) {
                try {
                    handleDelete(command);
                } catch (BrockException e) {
                    displayResponse(e.getMessage());
                }

            } else {
                Task task;
                try {
                    task = createTask(command);
                    Brock.tasks.add(task);
                    displayResponse("Got it. I've added this task:\n"
                            + "  "
                            + getDetails(task)
                            + getTaskSummary());
                } catch (BrockException e) {
                    displayResponse(e.getMessage());
                }
            }
        }
    }
}

