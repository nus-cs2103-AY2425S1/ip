import Exceptions.BrockException;

import Tasks.Deadlines;
import Tasks.Events;
import Tasks.Task;
import Tasks.ToDos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Brock {
    enum Action {
        MARK,
        UNMARK
    }

    private static final String HORIZONTAL_LINE = "________________________________________________\n";
    private static final String FILE_PATH = "./src/main/java/saveFile.txt";
    private static final ArrayList<Task> tasks = new ArrayList<>();

    // Helper function to display response with lines
    private static void displayResponse(String response) {
        System.out.println(Brock.HORIZONTAL_LINE
                + response
                + '\n'
                + Brock.HORIZONTAL_LINE);
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
        } catch (NumberFormatException | NullPointerException e) {
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

    private static void createFile() throws IOException {
        // TODO: Consider implementing some form of slight delay
        File saveFile = new File(Brock.FILE_PATH);

        displayResponse("Creating parent directories for save file...");
        boolean directoryStatus = saveFile.getParentFile().mkdirs();
        if (!directoryStatus) {
            displayResponse("Parent directories already exists!");
        } else {
            displayResponse("Parent directories successfully created!");
        }

        displayResponse("Creating save file for tasks...");
        boolean status = saveFile.createNewFile();
        if (!status) {
            displayResponse("Save file already exists!");
        } else {
            displayResponse("Save file successfully created");
        }
    }

    private static void writeToFile() throws IOException {
        // Creates a file writer object in append mode
        FileWriter fw = new FileWriter(Brock.FILE_PATH, true);
    }

    // Helper function to get task details as a string
    private static String getDetails(Task task) {
        return "[" + task.getTaskType() + "]"
                + "[" + task.getStatusIcon() + "] "
                + task.getDescription()
                + task.getExtraInfo();
    }

    // Helper function to get task summary, detailing current number of tasks
    private static String getTaskSummary() {
        int totalTasks = numTasks();
        return "Now you have "
                + totalTasks
                + (totalTasks == 1
                ? " task in the list."
                : " tasks in the list.");
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
                    .append(getDetails(currTask))
                    .append('\n');
        }

        if (!Brock.tasks.isEmpty()) {
            // Remove last new line character
            // To prevent duplicates when displaying response
            tasksString.deleteCharAt(tasksString.length() - 1);
        }

        int totalTasks = numTasks();
        String responseBody;
        if (totalTasks == 0) {
            responseBody = "No current tasks!";
        } else {
            responseBody = tasksString.toString();
        }

        displayResponse((totalTasks == 1
                ? "Here is the task in your list:\n"
                : "Here are the tasks in your list:\n")
                + responseBody);
    }

    // Helper function to validate the status (mark/unmark) command
    private static void validateStatus(String command, Action action) throws BrockException {
        String actionName = action == Action.MARK
                ? "Mark"
                : "Unmark";
        String[] commandWords = command.split(" ");
        int commandLength = commandWords.length;

        if (commandLength == 1) {
            throw new BrockException("Missing task number!");
        }
        if (commandLength > 2 || isNotInteger(commandWords[1])) {
            throw new BrockException(actionName
                    + " command is in the form "
                    + actionName.toLowerCase()
                    + " <task-number>!");
        }

        int taskNumber = Integer.parseInt(commandWords[1]);
        int totalTasks = numTasks();
        if (taskNumber > totalTasks || taskNumber < 1) {
            throw new BrockException("Task number does not exist!");
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
            throw new BrockException("Missing task number!");
        }
        if (commandLength > 2 || isNotInteger(commandWords[1])) {
            throw new BrockException("Delete command is in the form delete <task-number>!");
        }

        int taskNumber = Integer.parseInt(commandWords[1]);
        int totalTasks = numTasks();
        if (taskNumber > totalTasks || taskNumber < 1) {
            throw new BrockException("Task number does not exist!");
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
                + '\n'
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
                throw new BrockException("Missing due date! Remember it is specified after /by!");
            }
            // Trim away the trailing whitespace
            task = new Deadlines(description.toString(),
                    dueDate.toString().trim());

        } else if (firstWord.equalsIgnoreCase("event")) {
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
                throw new BrockException("Missing start date! Remember it is specified after /from!");
            }
            if (endDate.isEmpty()) {
                throw new BrockException("Missing end date! Remember it is specified after /to!");
            }
            // Trim away the trailing whitespace
            task = new Events(description.toString(),
                    startDate.toString(),
                    endDate.toString().trim());

        } else {
            throw new BrockException("Invalid command!");
        }

        if (description.isEmpty()) {
            throw new BrockException("Description is missing!");
        }
        return task;
    }

    public static void main(String[] args) {
        // Create a scanner object
        // Reads from standard system input
        Scanner scanner = new Scanner(System.in);

        try {
            createFile();
        } catch (IOException e) {
            displayResponse("Error creating file!\n "
                    + "Please re-run the program and try again.");
            return;
        }

        // Initial message
        String initialResponse = "Hello! I'm Brock\n"
                + "What can I do for you?";
        displayResponse(initialResponse);

        // Main loop
        while (true) {
            // Trim away leading & trailing whitespaces
            // Replace multiple whitespaces with a single one
            String command = scanner.nextLine()
                    .trim()
                    .replaceAll(" +", " ");
            if (command.equalsIgnoreCase("bye")) {
                displayResponse("Bye. Hope to see you again soon!");
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
                            + '\n'
                            + getTaskSummary());
                } catch (BrockException e) {
                    displayResponse(e.getMessage());
                }
            }
        }
    }
}

