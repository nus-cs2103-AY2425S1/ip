import Exceptions.BrockException;

import Tasks.Deadlines;
import Tasks.Events;
import Tasks.Task;
import Tasks.ToDos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Scanner;

public class Brock {
    enum Action {
        MARK,
        UNMARK
    }

    enum Context {
        DUE,
        START,
        END
    }

    private static final String HORIZONTAL_LINE = "_____________________________________________________________________\n";
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
        String[] taskComponents = taskString.split("\\. ", 2);
        String taskDetails = taskComponents[1];
        char taskType = taskDetails.charAt(1);

        String description;
        switch (taskType) {
        case 'T':
            description = taskDetails.substring(7);
            return new ToDos(description);

        case 'D':
            String[] deadlineDetails = taskDetails.substring(7)
                    .split(" \\(by: ", 2);

            description = deadlineDetails[0];
            String dateTimeD = deadlineDetails[1];

            String[] dateTimeParts = dateTimeD.split(", ");
            String dueDateString;
            String dueTimeString = "";
            DateTimeFormatter dueDateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            if (dateTimeParts.length == 1) {
                // Only have date specified
                // Exclude the closing bracket
                String tempDate = dateTimeParts[0];
                String tempString = tempDate.substring(0, tempDate.length() - 1);

                dueDateString = LocalDate.parse(tempString, dueDateFormatter).toString();
            } else {
                // Have both date and time specified
                // Exclude the closing bracket
                String tempDate = dateTimeParts[0];
                String tempTime = dateTimeParts[1];
                dueDateString = LocalDate.parse(tempDate, dueDateFormatter).toString();
                dueTimeString = tempTime.substring(0, tempTime.length() - 1)
                        .replace(":", "");
            }

            if (dueTimeString.isEmpty()) {
                return new Deadlines(description, dueDateString);
            } else {
                return new Deadlines(description, dueDateString, dueTimeString);
            }

        case 'E':
            String[] eventDetails = taskDetails.substring(7)
                    .split(" \\(from: ", 2);
            description = eventDetails[0];
            String dateTimeE = eventDetails[1];

            String[] dateTimePartsE = dateTimeE.split(" \\| ", 2);
            String startDateTime = dateTimePartsE[0];
            String endDateTime = dateTimePartsE[1];

            String startDateString;
            String endDateString;
            String startTimeString = "";
            String endTimeString = "";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

            String[] startDateTimeParts = startDateTime.split(", ");
            String[] endDateTimeParts = endDateTime.substring(4).split(", "); // remove the 'to: '
            if (startDateTimeParts.length == 1) {
                // Only have date specified
                // Exclude the closing bracket
                String tempStartDate = startDateTimeParts[0];
                String tempEndDate = endDateTimeParts[0].substring(0, endDateTimeParts[0].length() - 1);
                startDateString = LocalDate.parse(tempStartDate, dateFormatter).toString();
                endDateString = LocalDate.parse(tempEndDate, dateFormatter).toString();
            } else {
                // Have both date and time specified
                String tempStartDate = startDateTimeParts[0];
                String tempEndDate = endDateTimeParts[0];
                startTimeString = startDateTimeParts[1].replace(":", "");
                endTimeString = endDateTimeParts[1].substring(0, endDateTimeParts[1].length() - 1)
                        .replace(":", "");

                startDateString = LocalDate.parse(tempStartDate, dateFormatter).toString();
                endDateString = LocalDate.parse(tempEndDate, dateFormatter).toString();
            }

            if (startTimeString.isEmpty()) {
                return new Events(description, startDateString, endDateString);
            } else {
                return new Events(description, startDateString, startTimeString
                        , endDateString, endTimeString);
            }

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

    private static String getLabel(int dateTimeWords, Context context) throws BrockException {
        String label = "";
        switch (context) {
        case DUE:
            label = "Due ";
            break;
        case START:
            label = "Start ";
            break;
        case END:
            label = "End ";
            break;
        }

        if (dateTimeWords > 2) {
            throw new BrockException(String.format("Valid %s date & time must follow one of the below formats:\n"
                    , label.toLowerCase())
                    + "<yyyy-mm-dd> OR\n"
                    + "<yyyy-mm-dd> <24hr-time>");
        }
        return label;
    }

    private static String[] validateDateTime(String dateTimeString, int dateTimeWords, Context context)
            throws BrockException {
        String label = getLabel(dateTimeWords, context);

        String dateStringFinal = "";
        String timeStringFinal = "";
        if (dateTimeWords == 1) {
            String dateString = dateTimeString.trim();
            String[] dateParts = dateString.split("-");

            if (dateParts.length != 3) {
                throw new BrockException(label + "date & time following <yyyy-mm-dd> format:\n"
                        + label + "date is not in the <yyyy-mm-dd> format!");
            }
            dateStringFinal = dateString;
        }
        if (dateTimeWords == 2) {
            String[] dateTimeParts = dateTimeString.trim()
                    .split(" ");
            String dateString = dateTimeParts[0];
            String timeString = dateTimeParts[1];
            String[] dateParts = dateString.split("-");

            if (dateParts.length != 3) {
                throw new BrockException(label + "date & time following <yyyy-mm-dd> <24hr-time> format:\n"
                        + label + "date is not in the <yyyy-mm-dd> format!");
            }
            if (isNotInteger(timeString)) {
                throw new BrockException(label + "date & time following <yyyy-mm-dd> <24hr-time> format:\n"
                        + label + "time is not a number!");
            } else {
                int time = Integer.parseInt(timeString);
                if (time < 0 || time > 2359) {
                    throw new BrockException(label + "date & time following <yyyy-mm-dd> <24hr-time> format:\n"
                            + label + "time must be between 0000 - 2359!");
                }
                // Convert to string
                // Force length == 4 with 0s as front-padding
                timeStringFinal = String.format("%04d", time);
            }
            dateStringFinal = dateString;
        }

        if (timeStringFinal.isEmpty()) {
            return new String[]{dateStringFinal};
        } else {
            return new String[]{dateStringFinal, timeStringFinal};
        }
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
            StringBuilder dateTime = new StringBuilder();
            boolean isSeeingDateTime = false;
            int dateTimeWords = 0;
            for (String word : commandWords) {
                if (isSeeingDateTime) {
                    dateTimeWords += 1;
                    dateTime.append(word)
                            .append(" ");
                }
                if (word.equalsIgnoreCase("/by")) {
                    isSeeingDateTime = true;
                }
            }

            if (dateTime.isEmpty()) {
                throw new BrockException("Missing due date! Remember it is specified after /by!");
            }
            String[] dateTimeValues = validateDateTime(dateTime.toString()
                    , dateTimeWords, Context.DUE);
            if (dateTimeWords == 1) {
                task = new Deadlines(description.toString()
                        , dateTimeValues[0]);
            } else {
                task = new Deadlines(description.toString()
                        , dateTimeValues[0]
                        , dateTimeValues[1]);
            }

        } else if (firstWord.equalsIgnoreCase("event")) {
            StringBuilder startDateTime = new StringBuilder();
            StringBuilder endDateTime = new StringBuilder();
            boolean isSeeingStartDateTime = false;
            boolean isSeeingEndDateTime = false;
            int startDateTimeWords = 0;
            int endDateTimeWords = 0;
            for (String word : commandWords) {
                if (word.equalsIgnoreCase("/from")) {
                    isSeeingStartDateTime = true;
                    continue;
                }
                if (word.equalsIgnoreCase("/to")) {
                    isSeeingStartDateTime = false;
                    isSeeingEndDateTime = true;
                    continue;
                }
                if (isSeeingStartDateTime) {
                    startDateTimeWords += 1;
                    startDateTime.append(word)
                            .append(" ");
                }
                if (isSeeingEndDateTime) {
                    endDateTimeWords += 1;
                    endDateTime.append(word)
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

            if (startDateTime.isEmpty()) {
                throw new BrockException("Missing start date! Remember it is specified after /from!");
            }
            if (endDateTime.isEmpty()) {
                throw new BrockException("Missing end date! Remember it is specified after /to!");
            }
            if (startDateTimeWords != endDateTimeWords) {
                throw new BrockException("Both start and end dates must either include or exclude a time!");
            }
            String[] startDateTimeValues = validateDateTime(startDateTime.toString()
                    , startDateTimeWords, Context.START);
            String[] endDateTimeValues = validateDateTime(endDateTime.toString()
                    , endDateTimeWords, Context.END);
            if (startDateTimeWords == 1) {
                task = new Events(description.toString()
                        , startDateTimeValues[0]
                        , endDateTimeValues[0]);
            } else {
                task = new Events(description.toString()
                        , startDateTimeValues[0]
                        , startDateTimeValues[1]
                        , endDateTimeValues[0]
                        , endDateTimeValues[1]);
            }

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

