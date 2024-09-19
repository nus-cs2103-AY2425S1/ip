package parser;
import Commands.*;
import task.TaskList;
import ui.Ui;

import exception.IncompleteDescException;
import exception.UnknownWordException;
import exception.InvalidDeadlineException;

import task.DeadlinesTask;
import task.EventTask;
import task.Task;
import task.ToDoTask;

import prince.Prince;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Parses input commands by user and performs the corresponding actions
 *
 * Class is responsible for interpreting input commands such as adding and deleting tasks,
 * marking tasks as done or undone and providing tasks in a list format when the user demands.
 * Class is also responsible for creating the various types of tasks and thus throws relevant
 * exceptions to ensure that commands are formatted correctly and the right tasks are created
 * according to the tasks.
 *
 */


public class Parser {

    /**
     * Parses the commands given by the user and performs the necessary actions
     * @param command
     * @return a String message in response to the command thrown and creates the corresponding
     * object/ executes the corresponding action
     * @throws UnknownWordException if the command is unknown
     * @throws IncompleteDescException if the command is not fully completed in the right format
     */
    public static String parseConversation(String command) throws UnknownWordException, IncompleteDescException {
        /*if(command.equals("bye")) { //string cannot do ==
            return "Bye! Hope to see you again soon!";*/

        if (command == null || command.trim().isEmpty()) {
            throw new IncompleteDescException("Task description cannot be empty. Please provide a valid description.");
        } else if (command.equals("list")) {

            ListCommand c = new ListCommand(command);
            return c.commandAction();

        } else if (command.equals("archivelist")) {

            ArchiveListCommand c = new ArchiveListCommand();
            return c.commandAction();

        } else if (command.trim().startsWith("find")) {

            FindCommand c = new FindCommand(command);
            return c.commandAction();

        } else if (command.startsWith("mark") || command.startsWith("unmark") ||
                command.startsWith("delete")) {

            MarkUnmarkDeleteCommand c = new MarkUnmarkDeleteCommand(command);
            return c.commandAction();


        } else if (command.equals("archive")) {

            ArchiveCommand c = new ArchiveCommand();
            c.commandAction();
            return "List archived successfully.";

        } else if (checkCommandLength(command)) {
            if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                throw new IncompleteDescException("OH NO! Description of the task cannot be empty!\n " +
                        "Please retry with a command like this format <task type> <task>");
            } else {
                throw new UnknownWordException("Unknown command detected: " + "'" + command + "'" + ".  Sorry, I do not know what that means :(\n" +
                        "Please try again with a proper command.\n" +
                        "Make sure you are not adding any unecessary spaces or characters.");
            }
        } else if (!checkUnknownCommand(command)) {
            throw new UnknownWordException("Unknown command detected: " + "'" + command + "'" + ".  Sorry, I do not know what that means :(\n" +
                    "Please try again with a proper command.\n" +
                    "Make sure you are not adding any unecessary spaces or characters.");
        } else {

            GeneralTaskCommand c = new GeneralTaskCommand(command);
            return c.commandAction();

        }
    }

    /**
     * Checks if command's length is one word
     * this method is used to check whether the commands are likely incomplete
     *
     * @param cmd
     * @return true if command length is only 1
     */
    public static boolean checkCommandLength(String cmd) {
        assert cmd != null : "Command should not be null.";

        String[] split = cmd.trim().split(" ", 2);
        return split.length == 1;
    }

    /**
     * Checks if command starts with a task type
     * this method is used to check whether the commands are likely unknown commands
     *
     * @param cmd
     * @return true if command length is only 1
     */
    public static boolean checkUnknownCommand(String cmd) {
        assert cmd != null : "Command should not be null.";
        return (cmd.startsWith("todo") || cmd.startsWith("deadline") || cmd.startsWith("event"));
    }


}
