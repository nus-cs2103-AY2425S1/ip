package yapbot.main;

import yapbot.exceptions.YapBotException;
import yapbot.util.Storage;
import yapbot.util.TaskList;
import yapbot.util.Ui;


import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Main class to start YapBot.
 */
public class YapBot {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public YapBot(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        this.taskList = new TaskList(storage.load());


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
                    shouldContinue = false;
                    saveTasks();
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
