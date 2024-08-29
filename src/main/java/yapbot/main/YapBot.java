package yapbot.main;

import yapbot.tasks.Deadline;
import yapbot.tasks.Event;
import yapbot.tasks.Task;
import yapbot.tasks.ToDo;
import yapbot.exceptions.YapBotException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Main class to start YapBot.
 */
public class YapBot {
    private static final String PREFIX_LINE = "\n-------------------------------------------";
    private static final String POSTFIX_LINE = "-------------------------------------------\n";
    private static ArrayList<Task> storedTasks = new ArrayList<>();
    private enum Tasktype {TODO, DEADLINE, EVENT};

    /**
     * Prints the list of tasks currently in storedTasks.
     *
     * @throws YapBotException If storedTasks is empty.
     */
    private static void list() throws YapBotException {
        if (storedTasks.isEmpty()) {
            throw new YapBotException("Error, no Tasks found in database.");
        }

        Iterator<Task> iterateStored = storedTasks.iterator();
        System.out.println(PREFIX_LINE + "\nRetrieving Tasks...Success\nCurrent Tasks:");

        int index = 1;
        while (iterateStored.hasNext()) {
            System.out.println("  " + index + "." + iterateStored.next());
            index++;
        }
        System.out.println(POSTFIX_LINE);
    }

    /**
     * Removes task in storedTasks.
     *
     * @param index Task number specifed by user. index = actual array index + 1.
     * @throws YapBotException If index > size of storedTasks array or index <= 0 (ie there is no task for the index).
     */
    private static void deleteTask(int index) throws YapBotException {

        if (index <= 0 || index >= storedTasks.size() + 1) {
            throw new YapBotException("Finding Task...Failure\nError, requested Task does not exist"
                    + ".\nUse command \"list\" to view your tasks.");
        }

        Task task = storedTasks.remove(index - 1);

        System.out.println(PREFIX_LINE + "\nFinding Task...Success\nTask deleted from database:\n  "
                + task + "\n" + POSTFIX_LINE);

    }

    /**
     * Sets a task to either completed or incomplete.
     *
     * @param shouldMark When this is true, tasks are completed. When false, tasks are marked as incomplete.
     * @param index Task number specifed by user. index = actual array index + 1.
     * @throws YapBotException If index > size of storedTasks array or index <= 0 (ie there is no task for the index).
     */
    private static void markOrUnmark(boolean shouldMark, int index) throws YapBotException {
        if (shouldMark) {

            if (index == 0 || index >= storedTasks.size() + 1) {
                throw new YapBotException("Finding Task...Failure\nError, requested Task does not exist"
                        + ".\nUse command \"list\" to view your tasks.");
            }

            Task task = storedTasks.get(index - 1);
            task.setDone(true);

            System.out.println(PREFIX_LINE + "\nFinding Task...Success\nTask Completed:\n  "
                    + task
                    + "\n" + POSTFIX_LINE);
        } else {
            if (index == 0 || index >= storedTasks.size() + 1) {
                throw new YapBotException("Finding Task...Failure\nError, requested Task does not exist"
                        + ".\nUse command \"list\" to view your tasks.");
            }

            Task task = storedTasks.get(index - 1);
            task.setDone(false);

            System.out.println(PREFIX_LINE + "\nFinding Task...Success\nTask Incomplete:\n  "
                    + task
                    + "\n" + POSTFIX_LINE);
        }
    }

    /**
     * Creates a task instance and stores it in storedTasks field.
     *
     * @param tasktype Type of task to be created (eg. ToDo, Event, etc).
     * @param taskDetails Details of the task to be created.
     * @throws YapBotException If task details are missing for the specified task type.
     */
    private static void createTask(Tasktype tasktype, String taskDetails) throws YapBotException {

        switch (tasktype) {
        case TODO: {
            Task task = new ToDo(taskDetails);
            storedTasks.add(task);

            System.out.println(PREFIX_LINE + "\nAdding Task...\nSuccess\nTask added to database:\n" + "  "
                    + task + "\n" + "Total tasks: " + storedTasks.size() + "\n" + POSTFIX_LINE);
            break;
        }

        case DEADLINE: {
            if (!taskDetails.contains("/by")) {
                throw new YapBotException("Error, Deadline Prediction module offline.\nSupply a deadline using "
                        + "\"/by\" (eg. /by Monday 1pm).");
            }

            String taskName = taskDetails.substring(0, taskDetails.indexOf("/by")).strip();
            String deadlineStr = taskDetails.substring(taskDetails.indexOf("/by") + 3).strip().toUpperCase();

            if (taskName.isEmpty()) {
                throw new YapBotException("Error, Automated Task Suggestion module offline.\nTask details"
                        + " must be manually entered.");
            }

            if (deadlineStr.isEmpty()) {
                throw new YapBotException("Error, Deadline Prediction module offline.\nManually input a "
                        + "deadline or use command \"todo\" for tasks without deadlines.");
            }

            LocalDateTime deadline;
            String extraOutput = "";

            if (deadlineStr.contains("AM") | deadlineStr.contains("PM")) {
                if (deadlineStr.contains("/")) {
                    deadline = LocalDateTime.parse(deadlineStr, DateTimeFormatter.ofPattern("ha yyyy/MM/dd"));
                } else {
                    deadline = LocalTime.parse(deadlineStr, DateTimeFormatter.ofPattern("ha")).atDate(LocalDate.now());
                }

            } else {
                deadline = LocalDate.parse(deadlineStr, DateTimeFormatter.ofPattern("yyyy/MM/dd")).atStartOfDay();
                extraOutput = "\nTime automatically set to 8am (default).";
            }

            Task task = new Deadline(taskName, deadline);
            storedTasks.add(task);

            System.out.println(PREFIX_LINE + "\nAdding Task...\nSuccess"
                    + extraOutput
                    + "\nTask added to database:\n" + "  "
                    + task + "\n" + "Total tasks: " + storedTasks.size() + "\n" + POSTFIX_LINE);
            break;
        }

        case EVENT: {
            if (!taskDetails.contains("/from") && !taskDetails.contains("/to")) {
                throw new YapBotException("Error, start and end times not detected.\nSupply start time using "
                        + "\"/from\" (eg. /from Monday 1pm).\nSupply end time using \"/to\" (eg. /to Tuesday 1pm).");
            }

            if (!taskDetails.contains("/from")) {
                throw new YapBotException("Error, start time not detected.\nSupply start time using \"/from\" (eg. "
                        + "/from Monday 1pm).");
            }

            if (!taskDetails.contains("/to")) {
                throw new YapBotException("Error, end time not detected.\nSupply end time using \"/to\" (eg. /to "
                        + "Tuesday 1pm).");
            }

            String taskName = taskDetails.substring(0, taskDetails.indexOf("/")).strip();
            String taskDeadlines = taskDetails.substring(taskDetails.indexOf("/"));
            int fromIndex = taskDeadlines.indexOf("/from");
            int toIndex = taskDeadlines.indexOf("/to");
            String fromStr;
            String toStr;

            // Checks order of /from and /to
            if (toIndex > fromIndex) {
                fromStr =
                        taskDeadlines.substring(taskDeadlines.indexOf("/from") + 5, taskDeadlines.indexOf("/to")).strip()
                                .toUpperCase();
                toStr = taskDeadlines.substring(taskDeadlines.indexOf("/to") + 3).strip().toUpperCase();
            } else {
                toStr = taskDeadlines.substring(taskDeadlines.indexOf("/to") + 3, taskDeadlines.indexOf("/from")).strip()
                        .toUpperCase();
                fromStr = taskDeadlines.substring(taskDeadlines.indexOf("/from") + 5).strip().toUpperCase();
            }

            if (taskName.isEmpty()) {
                throw new YapBotException("Error, Automated Task Suggestion module offline.\nTask details"
                        + " must be manually entered.");
            }

            if (toStr.isEmpty() && fromStr.isEmpty()) {
                throw new YapBotException("Error, start and end times not detected.\nUse command \"todo\" for tasks " +
                        "without deadlines.");
            }

            if (toStr.isEmpty()) {
                throw new YapBotException("Error, end time not detected.\nManual input of end time required.");
            }

            if (fromStr.isEmpty()) {
                throw new YapBotException("Error, start time not detected.\nUse command \"deadline\" for tasks "
                        + "without start times.");
            }

            LocalDateTime from;
            LocalDateTime to;
            String extraOutput = "";

            if (fromStr.contains("AM") | fromStr.contains("PM")) {
                if (fromStr.contains("/")) {
                    from = LocalDateTime.parse(fromStr, DateTimeFormatter.ofPattern("ha yyyy/MM/dd"));
                } else {
                    from = LocalTime.parse(fromStr, DateTimeFormatter.ofPattern("ha")).atDate(LocalDate.now());
                }

            } else {
                from = LocalDate.parse(fromStr, DateTimeFormatter.ofPattern("yyyy/MM/dd")).atTime(8,0);
                extraOutput = "\nTime automatically set to 8am (default).";
            }

            if (toStr.contains("AM") | toStr.contains("PM")) {
                if (toStr.contains("/")) {
                    to = LocalDateTime.parse(toStr, DateTimeFormatter.ofPattern("ha yyyy/MM/dd"));
                } else {
                    to= LocalTime.parse(toStr, DateTimeFormatter.ofPattern("ha")).atDate(LocalDate.now());
                }

            } else {
                to = LocalDate.parse(toStr, DateTimeFormatter.ofPattern("yyyy/MM/dd")).atTime(8, 0);

                extraOutput = "\nTime automatically set to 8am (default).";
            }

            Task task = new Event(taskName, from, to);
            storedTasks.add(task);

            System.out.println(PREFIX_LINE + "\nAdding Task...\nSuccess"
                    + extraOutput
                    + "\nTask added to database:\n" + "  "
                    + task + "\n" + "Total tasks: " + storedTasks.size() + "\n" + POSTFIX_LINE);
            break;
        }
        }
    }

    /**
     * Saves the tasks stored in storedTasks in a text file.
     *
     * @throws IOException If the file cannot be found.
     */
    private static void saveTasks() throws IOException {

        if (storedTasks.isEmpty()) {
            return;
        }

        FileWriter fileWriter = new FileWriter("./data/tasks.txt");
        Iterator<Task> iterateStored = storedTasks.iterator();

        while (iterateStored.hasNext()) {
            fileWriter.write(iterateStored.next().saveTask() + System.lineSeparator());
        }

        fileWriter.close();

    }

    /**
     * Loads tasks from file and creates the file if its does not exist.
     * If the file cannot be created, YapBot continues running but tasks will not be saved.
     * If load file is corrupted, previously loaded tasks are cleared from storedTasks and the file is overwritten.
     *
     * @return true if data is loaded successfully and false otherwise.
     */
    private static boolean loadTasks() {
        File file = new File("./data/tasks.txt");

        try {
            if (file.exists()) {
                Scanner s =  new Scanner(file);

                if (!s.hasNext()) {
                    return false;
                }

                while (s.hasNext()) {
                    String[] taskData = s.nextLine().split("/");
                    String taskType = taskData[0];

                    switch(taskType) {
                    case "T": {
                        int isDone = Integer.parseInt(taskData[1]);
                        String taskDetails = taskData[2];
                        Task task;

                        if (isDone == 1) {
                            task = new ToDo(taskDetails, true);
                        } else {
                            task = new ToDo(taskDetails);
                        }
                        storedTasks.add(task);
                        break;
                    }

                    case "D": {
                        int isDone = Integer.parseInt(taskData[1]);
                        String taskDetails = taskData[2];
                        LocalDateTime deadline = LocalDateTime.parse(taskData[3]);
                        Task task;

                        if (isDone == 1) {
                            task = new Deadline(taskDetails, deadline, true);
                        } else {
                            task = new Deadline(taskDetails, deadline);
                        }
                        storedTasks.add(task);
                        break;
                    }

                    case "E": {
                        int isDone = Integer.parseInt(taskData[1]);
                        String taskDetails = taskData[2];
                        LocalDateTime from = LocalDateTime.parse(taskData[3]);
                        LocalDateTime to = LocalDateTime.parse(taskData[4]);
                        Task task;

                        if (isDone == 1) {
                            task = new Event(taskDetails, from, to, true);
                        } else {
                            task = new Event(taskDetails, from, to);
                        }
                        storedTasks.add(task);
                        break;
                    }

                    default: {
                        file.delete();
                        storedTasks.clear();

                        new File("./data").mkdir();
                        file.createNewFile();

                        throw new YapBotException("\nSave data detected...load failed.\nCorrupted data found."
                                + "\nYapBot will execute without prior data.");
                    }
                    }
                }

                s.close();
                return true;
            } else {
                new File("./data").mkdir();
                file.createNewFile();

                return false;
            }

        } catch (IOException e) {
            System.out.println(PREFIX_LINE
                    + "\nError, unable to create new save file.\nYour current session will not be saved.\n"
                    + POSTFIX_LINE);

            return false;
        } catch (YapBotException e) {
            System.out.println(e.getMessage());

            return false;
        }
    }

    /**
     * Driver method to accept user input and parse commands.
     *
     * @param args Not needed.
     */
    public static void main(String[] args) {
        String MISSING_TASK_DETAILS_MESSAGE = "Error, Automated Task Suggestion module offline."
                + "\nTask details must be manually entered.";
        String MISSING_TASK_NUMBER_MESSAGE = "Error, User Input Prediction module offline."
                + "\nTask number must be manually entered (eg. \"1\", \"2\").";

        System.out.println(PREFIX_LINE
                + "\nPowering up...System booted successfully.\nYapBot online.\nExtensive damage detected."
                + "\nCore Systems 28% functional.\n"
                + POSTFIX_LINE);

        if (loadTasks()) {
            System.out.println(PREFIX_LINE
                    + "\nSave data detected...\n"
                    + storedTasks.size()
                    + " Tasks loaded Successfully.\nUse command \"list\" to view your tasks"
                    + ".\n"
                    + POSTFIX_LINE);
        }

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        boolean shouldContinue = true;

        while (shouldContinue) {
            try {
                // Remove leading and trailing whitespace from user input
                input = input.trim();
                int spaceCharPos = input.indexOf(" ");
                String command;
                String commandDetails;

                // Parse user input and separates out command from other details
                if (spaceCharPos == -1) {
                    command = input;
                    commandDetails = "";
                } else {
                    command = input.substring(0, input.indexOf(" "));
                    commandDetails = input.substring(input.indexOf(" ") + 1).strip();
                }

                if (command.isEmpty()) {
                    throw new YapBotException("Error, User Input Prediction module offline.\nManual input required.");
                }

                switch (command) {
                case "bye": {
                    in.close();
                    shouldContinue = false;
                    saveTasks();
                    System.out.println(PREFIX_LINE + "\nShutting down...\nYapBot process terminated.\n" + POSTFIX_LINE);
                    break;
                }

                case "list": {
                    list();
                    break;
                }

                case "mark": {
                    if (commandDetails.isEmpty()) {
                        throw new YapBotException(MISSING_TASK_NUMBER_MESSAGE);
                    }

                    int index;

                    if (commandDetails.contains(" ")) {
                        index = Integer.parseInt(commandDetails.substring(0, commandDetails.indexOf(" ")));
                    } else {
                        index = Integer.parseInt(commandDetails);
                    }

                    markOrUnmark(true, index);
                    break;
                }

                case "unmark": {
                    if (commandDetails.isEmpty()) {
                        throw new YapBotException(MISSING_TASK_NUMBER_MESSAGE);
                    }

                    int index;

                    if (commandDetails.contains(" ")) {
                        index = Integer.parseInt(commandDetails.substring(0, commandDetails.indexOf(" ")));
                    } else {
                        index = Integer.parseInt(commandDetails);
                    }

                    markOrUnmark(false, index);
                    break;
                }

                case "todo": {
                    if (commandDetails.isEmpty()) {
                        throw new YapBotException(MISSING_TASK_DETAILS_MESSAGE);
                    }

                    createTask(Tasktype.TODO, commandDetails);
                    break;
                }

                case "deadline": {
                    if (commandDetails.isEmpty()) {
                        throw new YapBotException(MISSING_TASK_DETAILS_MESSAGE);
                    }

                    createTask(Tasktype.DEADLINE, commandDetails);
                    break;
                }

                case "event": {
                    if (commandDetails.isEmpty()) {
                        throw new YapBotException(MISSING_TASK_DETAILS_MESSAGE);
                    }

                    createTask(Tasktype.EVENT, commandDetails);
                    break;
                }

                case "delete": {
                    if (commandDetails.isEmpty()) {
                        throw new YapBotException(MISSING_TASK_NUMBER_MESSAGE);
                    }

                    int index;

                    if (commandDetails.contains(" ")) {
                        index = Integer.parseInt(commandDetails.substring(0, commandDetails.indexOf(" ")));
                    } else {
                        index = Integer.parseInt(commandDetails);
                    }

                    deleteTask(index);
                    break;
                }

                default:
                    throw new YapBotException("Error, supporting module for user command: \"" + command + "\" not "
                            + "found.\nYapBot may not support this feature.");

                }
            } catch (NumberFormatException e) {
                System.out.println(PREFIX_LINE + "\nError, Natural Language Processing Module offline...\nSpecify "
                        + "Task number instead (eg. \"1\", \"2\").\n" + POSTFIX_LINE);
            } catch (YapBotException e) {
                System.out.println(e.getMessage());

            } catch (IOException e) {

                System.out.println(PREFIX_LINE + "\nShutting down...\nFatal error: Save failed."
                        + "\nYour tasks may not load when YapBot starts again."
                        + "\nYapBot process terminated.\n" + POSTFIX_LINE);
            } catch (DateTimeParseException e) {
                System.out.println(PREFIX_LINE + "\nError, Dynamic DateTime Module offline."
                        + "\nDate & Time should be one of these formats:"
                        + "\n  Date & Time - \"5pm 2024/09/01\""
                        + "\n  Date Only - \"2024/09/01\""
                        + "\n  Time Only - \"5pm\"\n"
                        + POSTFIX_LINE);
            }

            // Wait for next input from user unless bye command was given.
            if (shouldContinue) {
                input = in.nextLine();
            }
        }


    }
}
