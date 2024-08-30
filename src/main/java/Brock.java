import Exceptions.BrockException;

import Tasks.Deadlines;
import Tasks.Events;
import Tasks.Task;
import Tasks.ToDos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Brock {
    enum Action {
        MARK,
        UNMARK
    }

    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";
    private static final String FILE_PATH = "./src/main/java/saveFile.txt";

    private static final File saveFile = new File(FILE_PATH);
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

    private static void createFile() throws IOException {
        String dirStatus;
        boolean isDirectoryMissing = Brock.saveFile.getParentFile().mkdirs();
        if (!isDirectoryMissing) {
            dirStatus = "Parent directories already exists!";
        } else {
            dirStatus = "Parent directories successfully created!";
        }
        displayResponse("Creating parent directories for save file...\n"
                + dirStatus);

        String fileStatus;
        boolean isFileMissing = Brock.saveFile.createNewFile();
        if (!isFileMissing) {
            fileStatus = "Save file already exists!";
        } else {
            fileStatus = "Save file successfully created";
        }
        displayResponse("Creating save file for tasks...\n"
                + fileStatus);
    }

    // Read from file with no exclusion
    private static String readFromFile() throws BrockException {
        // Use SB as it is a faster way to append strings
        StringBuilder tasksString = new StringBuilder();
        try {
            Scanner s = new Scanner(Brock.saveFile);
            while (s.hasNext()) {
                tasksString.append(s.nextLine())
                        .append('\n');
            }
        } catch (FileNotFoundException e) {
            throw new BrockException("Unable to find and read from save file!");
        }

        if (!Brock.tasks.isEmpty()) {
            // Remove last new line character
            // To prevent duplicates when displaying response
            tasksString.deleteCharAt(tasksString.length() - 1);
        }
        return tasksString.toString();
    }

    // Read from file with exclusion
    // Exclusion is the task number to be excluded (ie: deleted)
    private static String readFromFile(int exclusion) throws BrockException {
        // Use SB as it is a faster way to append strings
        StringBuilder tasksString = new StringBuilder();
        try {
            Scanner s = new Scanner(Brock.saveFile);
            int count = 1;
            boolean hasSeenExclusion = false;
            while (s.hasNext()) {
                if (count == exclusion) {
                    s.nextLine();
                    count += 1;
                    hasSeenExclusion = true;
                    continue;
                }
                if (hasSeenExclusion) {
                    // s.next() reads: "<task number>."
                    // remove the ., modify the task number, append to string builder
                    String nextToken = s.next();
                    String taskNumber = nextToken.substring(0, nextToken.length() - 1);
                    int newTaskNumber = Integer.parseInt(taskNumber) - 1;
                    tasksString.append(newTaskNumber)
                            .append('.')
                            .append(s.nextLine());
                } else {
                    tasksString.append(s.nextLine())
                            .append('\n');
                }
                count += 1;
            }
        } catch (FileNotFoundException e) {
            throw new BrockException("Unable to find and read from save file!");
        }

        if (!Brock.tasks.isEmpty()) {
            // Remove last new line character
            // To prevent duplicates when displaying response
            tasksString.deleteCharAt(tasksString.length() - 1);
        }
        return tasksString.toString();
    }

    private static void writeToFile(String writeContent, boolean isAppendMode) throws IOException {
        FileWriter fw = new FileWriter(Brock.FILE_PATH, isAppendMode);
        fw.write(writeContent);
        fw.close();
    }

    private static Task convertToTaskObject(String taskString) throws BrockException {
        // Split by ". "
        String[] taskParts = taskString.split("\\. ", 2);
        String details = taskParts[1];
        char taskType = details.charAt(1);

        String description;
        String[] detailPartsV1;
        String[] detailPartsV2;
        String[] detailPartsV3;
        switch (taskType) {
        case 'T':
            description = details.substring(7);
            return new ToDos(description);

        case 'D':
            detailPartsV1 = details.split(" ");
            description = detailPartsV1[1];

            detailPartsV2 = details.split("\\(by: ");
            // Exclude the closing bracket
            String dueDate = detailPartsV2[1].substring(0, detailPartsV2[1].length() - 2);
            return new Deadlines(description, dueDate);

        case 'E':
            detailPartsV1 = details.split(" ");
            description = detailPartsV1[1];

            detailPartsV2 = details.split("\\(from: ");
            detailPartsV3 = detailPartsV2[1].split("to: ");
            String startDate = detailPartsV3[0];
            // Exclude the closing bracket
            String endDate = detailPartsV3[1].substring(0, detailPartsV3[1].length() - 2);
            return new Events(description, startDate, endDate);

        default:
            throw new BrockException("Unrecognized task type!");
        }
    }

    private static void loadTasksFromFile() throws BrockException {
        try {
            Scanner s = new Scanner(Brock.saveFile);
            while (s.hasNext()) {
                String taskString = s.nextLine();
                Task task = convertToTaskObject(taskString);
                Brock.tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new BrockException("Unable to find and read from save file!");
        }

    }

    // Helper function to perform list command
    private static void handleList() throws BrockException {
        String tasksString = readFromFile();
        int totalTasks = numTasks();

        String responseBody;
        if (totalTasks == 0) {
            responseBody = "No current tasks!";
        } else {
            responseBody = tasksString;
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
    private static void handleDelete(String command) throws BrockException, IOException {
        validateDelete(command);

        int targetIndex = getTargetIndex(command);
        Task removedTask = Brock.tasks.get(targetIndex);
        Brock.tasks.remove(targetIndex);
        displayResponse("Noted. I've removed this task:\n"
                + "  "
                + getDetails(removedTask)
                + '\n'
                + getTaskSummary());

        String fileContents = readFromFile(targetIndex + 1);
        writeToFile("", false);
        writeToFile(fileContents, true);
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
            boolean hasSeenDueDate = false;
            for (String word : commandWords) {
                if (hasSeenDueDate) {
                    dueDate.append(word)
                            .append(" ");
                }
                if (word.equalsIgnoreCase("/by")) {
                    hasSeenDueDate = true;
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
            boolean hasSeenStartDate = false;
            boolean hasSeenEndDate = false;
            for (String word : commandWords) {
                if (word.equalsIgnoreCase("/from")) {
                    hasSeenStartDate = true;
                    continue;
                }
                if (word.equalsIgnoreCase("/to")) {
                    hasSeenStartDate = false;
                    hasSeenEndDate = true;
                    continue;
                }
                if (hasSeenStartDate) {
                    startDate.append(word)
                            .append(" ");
                }
                if (hasSeenEndDate) {
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

        // Initialize the ArrayList
        // With any pre-existing tasks from save file
        try {
            loadTasksFromFile();
        } catch (BrockException e) {
            displayResponse(e.getMessage());
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
                try {
                    handleList();
                } catch (BrockException e) {
                    displayResponse(e.getMessage());
                }

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
                } catch (IOException e) {
                    displayResponse("Failed to reset the save file!");
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

                    String taskNumber = Integer.toString(numTasks());
                    writeToFile(taskNumber
                                    + ". "
                                    + getDetails(task)
                                    + '\n'
                            , true);
                } catch (BrockException e) {
                    displayResponse(e.getMessage());
                } catch (IOException e) {
                    displayResponse("Failed to write task into save file!");
                }
            }
        }
    }
}

