package yapbot.main;

import yapbot.tasks.Deadline;
import yapbot.tasks.Event;
import yapbot.tasks.Task;
import yapbot.tasks.ToDo;
import yapbot.exceptions.YapBotException;

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
    public static void list() throws YapBotException {
        if (storedTasks.isEmpty()) {
            throw new YapBotException("Error, no Tasks found in database.");
        }

        Iterator<Task> iterateStored = storedTasks.iterator();
        System.out.println(PREFIX_LINE + "\nRetrieving Tasks...\nSuccess\nCurrent Tasks:");

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
    public static void deleteTask(int index) throws YapBotException {

        if (index <= 0 || index >= storedTasks.size() + 1) {
            throw new YapBotException("Finding Task...\nFailure\nError, requested Task does not exist"
                    + ".\nUse command \"list\" to view your tasks.");
        }

        Task task = storedTasks.remove(index - 1);

        System.out.println(PREFIX_LINE + "\nFinding Task...\nSuccess\nTask deleted from database:\n  "
                + task + "\n" + POSTFIX_LINE);

    }

    /**
     * Sets a task to either completed or incomplete.
     *
     * @param shouldMark When this is true, tasks are completed. When false, tasks are marked as incomplete.
     * @param index Task number specifed by user. index = actual array index + 1.
     * @throws YapBotException If index > size of storedTasks array or index <= 0 (ie there is no task for the index).
     */
    public static void markOrUnmark(boolean shouldMark, int index) throws YapBotException {
        if (shouldMark) {

            if (index == 0 || index >= storedTasks.size() + 1) {
                throw new YapBotException("Finding Task...\nFailure\nError, requested Task does not exist"
                        + ".\nUse command \"list\" to view your tasks.");
            }

            Task task = storedTasks.get(index - 1);
            task.setDone(true);

            System.out.println(PREFIX_LINE + "\nFinding Task...\nSuccess\nTask Completed:\n  "
                    + task
                    + "\n" + POSTFIX_LINE);
        } else {
            if (index == 0 || index >= storedTasks.size() + 1) {
                throw new YapBotException("Finding Task...\nFailure\nError, requested Task does not exist"
                        + ".\nUse command \"list\" to view your tasks.");
            }

            Task task = storedTasks.get(index - 1);
            task.setDone(false);

            System.out.println(PREFIX_LINE + "\nFinding Task...\nSuccess\nTask Incomplete:\n  "
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
    public static void createTask(Tasktype tasktype, String taskDetails) throws YapBotException {

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
            String deadline = taskDetails.substring(taskDetails.indexOf("/by") + 3).strip();

            if (taskName.isEmpty()) {
                throw new YapBotException("Error, Automated Task Suggestion module offline.\nTask details"
                        + " must be manually entered.");
            }

            if (deadline.isEmpty()) {
                throw new YapBotException("Error, Deadline Prediction module offline.\nManually input a "
                        + "deadline or use command \"todo\" for tasks without deadlines.");
            }

            Task task = new Deadline(taskName, deadline);
            storedTasks.add(task);

            System.out.println(PREFIX_LINE + "\nAdding Task...\nSuccess\nTask added to database:\n" + "  "
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
            String from;
            String to;

            // Checks order of /from and /to
            if (toIndex > fromIndex) {
                from = taskDeadlines.substring(taskDeadlines.indexOf("/from") + 5, taskDeadlines.indexOf("/to")).strip();
                to = taskDeadlines.substring(taskDeadlines.indexOf("/to") + 3).strip();
            } else {
                to = taskDeadlines.substring(taskDeadlines.indexOf("/to") + 3, taskDeadlines.indexOf("/from")).strip();
                from = taskDeadlines.substring(taskDeadlines.indexOf("/from") + 5).strip();
            }

            if (taskName.isEmpty()) {
                throw new YapBotException("Error, Automated Task Suggestion module offline.\nTask details"
                        + " must be manually entered.");
            }

            if (to.isEmpty() && from.isEmpty()) {
                throw new YapBotException("Error, start and end times not detected.\nUse command \"todo\" for tasks " +
                        "without deadlines.");
            }

            if (to.isEmpty()) {
                throw new YapBotException("Error, end time not detected.\nManual input of end time required.");
            }

            if (from.isEmpty()) {
                throw new YapBotException("Error, start time not detected.\nUse command \"deadline\" for tasks "
                        + "without start times.");
            }

            Task task = new Event(taskName, from, to);
            storedTasks.add(task);

            System.out.println(PREFIX_LINE + "\nAdding Task...\nSuccess\nTask added to database:\n" + "  "
                    + task + "\n" + "Total tasks: " + storedTasks.size() + "\n" + POSTFIX_LINE);
            break;
        }
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
                + "\nPowering up...\nSystem booted successfully.\nYapBot online.\nExtensive damage detected."
                + "\nCore Systems 28% functional.\n"
                + POSTFIX_LINE);

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

            }

            // Wait for next input from user unless bye command was given.
            if (shouldContinue) {
                input = in.nextLine();
            }
        }


    }
}
