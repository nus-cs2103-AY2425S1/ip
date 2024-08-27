import java.io.IOException;
import java.io.FileWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Jeff {
    private static final String HORIZONTAL =
            "_____________________________________________________________________________________";
    private static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Encloses the text with horizontal lines and indents the text before printing it out
     *
     * @param text The string to be enclosed
     */
    private static void printText(String text) {
        System.out.println(indentText(HORIZONTAL + "\n " + text + "\n" + HORIZONTAL));
    }

    /**
     * Returns the same text but indented. If the text has multiple lines, each line will be indented.
     *
     * @param text the string to be indented
     * @return indented text
     */
    private static String indentText(String text) {
        // Split the text into lines
        String[] lines = text.split("\n");

        // Initialise a StringBuilder
        StringBuilder indentedText = new StringBuilder();

        // Add indentation to each line
        for (String line : lines) {
            indentedText.append("\t").append(line).append("\n");
        }

        // Convert the StringBuilder back to a String
        return indentedText.toString();

    }

    /**
     * Prints out a greeting message
     */
    private static void printGreetings() {
        printText("Hello! I'm Jeff.\n What can I do for you?");
    }

    /**
     * Prints out a farewell message
     */
    private static void printFarewell() {
        printText("Bye. Hope to see you again soon!");
    }

    /**
     * Prints out the task list
     *
     * @throws JeffException if the task list is empty
     */
    private static void printList() throws JeffException {
        // Check if the list is empty
        if (taskList.isEmpty()) {
            throw new JeffException("List is empty!");
        }

        // Initialise a StringBuilder
        StringBuilder listString = new StringBuilder();

        // Loop through the inputList and add it to the StringBuilder
        for (int i = 0; i < taskList.size(); i++) {
            listString.append(Integer.toString(i + 1)).append(".").append(taskList.get(i).toString());

            // Only add a new line when it is not the last task in the list
            if (i != taskList.size() - 1) {
                listString.append("\n ");
            }

        }

        // Print the text
        printText(listString.toString());

    }

    /**
     * Gets the target task from the task list based on the user input
     *
     * @param input String that the user types into the command line interface
     * @param prefix the action that the user wants to take
     * @return the target task from the task list
     * @throws JeffException if the input is in the wrong format or if the task number specified by the user does not
     *                       exist
     */
    private static Task getTask(String input, String prefix) throws JeffException {
        // Check if input is valid
        if (!input.matches(prefix + "\\d+")) {
            throw new JeffException("The format is wrong! It should be \"" + prefix + "xx\", where xx is a number.");
        }

        // Get the taskIndex
        int taskIndex = Integer.parseInt(input.substring(prefix.length())) - 1;

        // Check if taskIndex exists in taskList
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new JeffException("This task number does not exist!");
        }

        // Get the task from taskList and return it
        return taskList.get(taskIndex);

    }

    /**
     * Marks the target task as done
     *
     * @param input String that the user types into the command line interface
     * @throws JeffException from getTask or if the task has already been marked as done before this
     */
    private static void markTask(String input) throws JeffException {
        // Get the task from taskList
        Task targetTask = getTask(input, "mark ");

        // Check if the task has been done or not
        if (targetTask.isDone()) {
            // Tell the user that it already is done
            throw new JeffException("This task has already been marked as done!");

        } else {
            // Mark the task as done
            targetTask.markAsDone();

            // Print the task text
            printText("OK, I've marked this task as done:\n   " + targetTask.toString());
        }
    }

    /**
     * Marks the target task as not done
     *
     * @param input String that the user types into the command line interface
     * @throws JeffException from getTask or if the task has already been marked as not done before this
     */
    private static void unmarkTask(String input) throws JeffException {
        // Get the task from taskList
        Task targetTask = getTask(input, "unmark ");

        // Check if the task has been done or not
        if (targetTask.isDone()) {
            // Unmark the task
            targetTask.markAsNotDone();

            // Print the task text
            printText("OK, I've marked this task as not done yet:\n   " + targetTask.toString());

        } else {
            // Tell the user that it already is not done
            throw new JeffException("This task has already been marked as not done yet!");

        }

    }

    /**
     * Deletes the target task from the task list
     *
     * @param input String that the user types into the command line interface
     * @throws JeffException from getTask
     */
    private static void deleteTask(String input) throws JeffException {
        // Get the task from taskList
        Task targetTask = getTask(input, "delete ");

        // Delete the task
        taskList.remove(targetTask);

        // Print delete statement
        printText("Noted. I've removed this task:\n   " +
                targetTask.toString() +
                "\n Now you have " + taskList.size() + " tasks in the list.");
    }

    private static LocalDateTime getLocalDateTime(String input) throws DateTimeParseException {
        try {
            return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a"));
        } catch (DateTimeParseException e) {
            return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
    }

    /**
     * Figures out the task type, adds it to the task list and prints out the task addition statement
     *
     * @param input String that the user types into the command line interface
     * @throws JeffException if the input is in the wrong format
     */
    private static void handleTask(String input) throws JeffException {
        // Check if the input is in the correct format
        if (input.matches("todo .+") ||
                input.matches("deadline .+") ||
                input.matches("event .+")) {

            // Split the string to obtain the task type and task description
            String[] taskParts = input.split(" ", 2);
            String taskType = taskParts[0];
            String taskDescription = taskParts[1];

            // Initialise the task
            Task targetTask = null;

            // Check the task type and initialise accordingly
            switch (taskType) {
            case "todo":
                targetTask = new ToDoTask(taskDescription);
                break;
            case "deadline":
                // Split the description into content and deadline
                String[] deadlineParts = taskDescription.split(" /by ", 2);
                String deadlineContent = deadlineParts[0];
                String deadlinePeriod = deadlineParts.length > 1 ? deadlineParts[1] : "";

                // Check if the format is correct
                try {
                    targetTask = new DeadlineTask(deadlineContent, getLocalDateTime(deadlinePeriod));
                } catch (DateTimeParseException e) {
                    throw new JeffException(
                            "The format is wrong! It should be \"deadline xx /by yyyy-mm-dd HH:mm or hh:mm AM/PM\"!");
                }
                break;

            case "event":
                // Split the description into content, start and end
                String[] eventParts = taskDescription.split(" /from ", 2);
                String eventContent = eventParts[0];
                String eventPeriod = eventParts.length > 1 ? eventParts[1] : "";
                String[] eventPeriodParts = eventPeriod.split(" /to ", 2);
                String startPeriod = eventPeriodParts[0];
                String endPeriod = eventPeriodParts.length > 1 ? eventPeriodParts[1] : "";

                // Check if the format is correct
                try {
                    targetTask = new EventTask(
                            eventContent, getLocalDateTime(startPeriod), getLocalDateTime(endPeriod));
                } catch (DateTimeParseException e) {
                    throw new JeffException(
                            "The format is wrong! " +
                                    "It should be \"event xx /from yyyy-mm-dd HH:mm /to yyyy-mm--dd HH:mm\"!");
                }
                break;

            default:
                break;
            }

            if (targetTask != null) {
                // Add the task to the taskList
                taskList.add(targetTask);

                // Print the task out
                printText("Got it. I've added this task:\n   " +
                        targetTask.toString() +
                        "\n Now you have " + taskList.size() + " tasks in the list.");
            }

        } else {
            if (input.equals("todo") || input.equals("todo ")) {
                throw new JeffException("Sorry, the description of a todo cannot be empty!");
            } else if (input.equals("deadline") || input.equals("deadline ")) {
                throw new JeffException("Sorry, the description of a deadline cannot be empty!");
            } else if (input.equals("event") || input.equals("event ")) {
                throw new JeffException("Sorry, the description of a event cannot be empty!");
            } else {
                throw new JeffException();
            }
        }
    }

    /**
     * Gets the user input from the command line interface and figure out the action to take based on the input.
     * This is continuously repeated until the user says "bye"
     */
    private static void printUserInput() {
        // Initialise the Scanner
        Scanner scanner = new Scanner(System.in);
        String input = "";

        // Continue looping until input is bye
        while (!input.equals("bye")) {
            // Prompt the user for input
            System.out.print("");

            // Get the input
            input = scanner.nextLine();

            try {
                // Check for input == some keyword or starts with some keyword
                if (input.equals("list")) {
                    printList();
                } else if (input.equals("bye")) {
                    continue;
                } else if (input.startsWith("mark")) {
                    markTask(input);
                } else if (input.startsWith("unmark")) {
                    unmarkTask(input);
                } else if (input.startsWith("delete")) {
                    deleteTask(input);
                } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                    handleTask(input);
                } else {
                    throw new JeffException();
                }
            } catch (JeffException e) {
                printText(e.toString());
            }

        }

        // Close the Scanner
        scanner.close();
    }

    private static Path getTaskFilePath() throws URISyntaxException {
        // Use the current working directory and create a task text file there
        Path currentPath = Paths.get(System.getProperty("user.dir"));
        return currentPath.resolve("tasks.txt").normalize();
    }

    private static void getTasksFromDatabase() {
        try {
            // Get the task file location
            Path taskFilePath = getTaskFilePath();

            // Create a task file if it doesn't exist
            if (!Files.exists(taskFilePath)) {
                Files.createFile(taskFilePath);
            }

            // Use a scanner to read the data file
            Scanner scanner = new Scanner(taskFilePath);
            while (scanner.hasNext()) {
                // Get the task information
                String[] taskParts = scanner.nextLine().split(" \\| ");

                // Check if the data file is corrupted
                if (taskParts.length < 3) {
                    throw new FileCorruptException();
                }

                String taskType = taskParts[0];
                boolean isDone = taskParts[1].equals("1");
                String taskDescription = taskParts[2];
                Task currentTask = null;

                // Categorise and initialise the task based on its task type
                try {
                    switch (taskType) {
                    case "T":
                        // To Do Task
                        currentTask = new ToDoTask(taskDescription, isDone);
                        break;
                    case "D":
                        // Deadline Task
                        if (taskParts.length >= 4) {
                            currentTask = new DeadlineTask(taskDescription,
                                    LocalDateTime.parse(taskParts[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                                    isDone);
                        }
                        break;
                    case "E":
                        // Event Task
                        if (taskParts.length >= 5) {
                            currentTask = new EventTask(taskDescription,
                                    LocalDateTime.parse(taskParts[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                                    LocalDateTime.parse(taskParts[4], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                                    isDone);
                        }
                        break;
                    default:
                        break;
                    }
                } catch (DateTimeParseException e) {
                    throw new FileCorruptException();
                }


                // Add task to task list if it exists
                if (currentTask != null) {
                    taskList.add(currentTask);
                } else {
                    throw new FileCorruptException();
                }
            }

        } catch (IOException | URISyntaxException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } catch (FileCorruptException e) {
            System.out.println(e.toString());
        }
    }

    private static void writeTasksToDatabase() {
        try {
            // Map the tasks in the task list into their file strings
            List<String> fileStringList = taskList.stream()
                    .map(Task::toFileString)
                    .toList();

            // Write the strings into the data file
            Path taskFilePath = getTaskFilePath();
            if (!Files.exists(taskFilePath)) {
                Files.createFile(taskFilePath);
            }
            Files.write(taskFilePath, fileStringList);
        } catch (IOException | URISyntaxException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        getTasksFromDatabase();
        printGreetings();
        printUserInput();
        writeTasksToDatabase();
        printFarewell();
    }
}
