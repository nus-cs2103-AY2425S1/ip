package parser;

import exception.TakoException;
import tako.Tako;
import task.Event;
import task.Deadline;
import task.TaskList;
import task.ToDo;
import ui.Ui;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeParseException;


/**
 * Parse all commands that is
 * being input into Tako chatbot.
 */
public class Parser {

    /**
     * Checks whether the input command is recognizable by Tako.
     *
     * @param command the string that is being input into Tako.
     * @throws TakoException if the command is not recognized.
     */
    public static String parse(String command) {
        String output;
        try {
            if (command.equalsIgnoreCase("bye")) {
                output = Ui.exit();
            } else if (command.equalsIgnoreCase("list")) {
                output = Ui.printList();
            } else if (command.toLowerCase().startsWith("mark")) {
                output = parseMark(command);
            } else if (command.toLowerCase().startsWith("unmark")) {
                output = parseUnmark(command);
            } else if (command.toLowerCase().startsWith("todo")) {
                output = parseTodo(command);
            } else if (command.toLowerCase().startsWith("deadline")) {
                output = parseDeadline(command);
            } else if (command.toLowerCase().startsWith("event")) {
                output = parseEvent(command);
            } else if (command.toLowerCase().startsWith("delete")) {
                output = parseDelete(command);
            } else if (command.toLowerCase().startsWith("find")) {
                output = parseFind(command);
            } else if (command.toLowerCase().startsWith("priority")) {
                output = parsePriority(command);
            } else {
                throw new TakoException("command");
            }
        } catch (TakoException e) {
            return e.message();
        }
        return output;
    }


    public static String parsePriority(String command) {
        try {
            String[] commandDetails = command.trim().split(" ");
            if (commandDetails.length != 3 || !commandDetails[0].equalsIgnoreCase("priority")) {
                throw new TakoException("priority");
            }
            String commandPriority = commandDetails[1];
            if (!commandPriority.equalsIgnoreCase("low") &&
                    !commandPriority.equalsIgnoreCase("medium") &&
                    !commandPriority.equalsIgnoreCase("high")) {
                throw new TakoException("priority");
            }
            try {
                int taskNumber = Integer.parseInt(commandDetails[2]);
                return TaskList.changePriority(commandPriority.toLowerCase(), taskNumber - 1);
            } catch (NumberFormatException e) {
                throw new TakoException("priority");
            }
        } catch (TakoException e) {
            return e.message();
        }
    }
    /**
     * Finds the task which consists of the 'command' input
     *
     * @param command find command input by user
     * @throws TakoException if the find command is not in the right form
     */
    public static String parseFind(String command) {
        try {
            String[] commandDetails = command.trim().split(" ", 2);
            if (commandDetails.length != 2 || !commandDetails[0].equalsIgnoreCase("find")) {
                throw new TakoException("find");
            }
            String targetString = commandDetails[1];
            return Ui.findTargetString(targetString);
        } catch (TakoException e) {
            return e.message();
        }
    }

    /**
     * Marks the task as complete according to the input
     *
     * @param command mark command input by user
     * @throws TakoException if the mark command is not in the right form
     */
    public static String parseMark(String command) {
        try {
            String[] commandDetails = command.trim().split(" ", 2);
            if (commandDetails.length != 2 || !commandDetails[0].equalsIgnoreCase("mark")) {
                throw new TakoException("mark");
            }
            try {
                int taskNumber = Integer.parseInt(commandDetails[1]);
                return TaskList.markTask(taskNumber);
            } catch (NumberFormatException e) {
                throw new TakoException("mark");
            }
        } catch (TakoException e) {
            return e.message();
        }
    }

    /**
     * Marks the task as incomplete according to the input
     *
     * @param command unmark command input by user
     * @throws TakoException if the unmark command is not in the right form
     */
    public static String parseUnmark(String command) {
        try {
            String[] commandDetails = command.trim().split(" ", 2);
            if (commandDetails.length != 2 || !commandDetails[0].equalsIgnoreCase("unmark")) {
                throw new TakoException("unmark");
            }
            try {
                int taskNumber = Integer.parseInt(commandDetails[1]);
                return TaskList.unmarkTask(taskNumber);
            } catch (NumberFormatException e) {
                throw new TakoException("unmark");
            }
        } catch (TakoException e) {
            return e.message();
        }
    }

    /**
     * Deletes the task according to the input from the list
     *
     * @param command delete command input by user
     * @throws TakoException if the delete command is not in the right form
     */
    public static String parseDelete(String command) {
        try {
            String[] commandDetails = command.trim().split(" ", 2);
            if (commandDetails.length != 2 || !commandDetails[0].equalsIgnoreCase("delete")) {
                throw new TakoException("delete");
            }
            try {
                int taskNumber = Integer.parseInt(commandDetails[1]);
                return TaskList.deleteTask(taskNumber);
            } catch (NumberFormatException e) {
                throw new TakoException("delete");
            }
        } catch (TakoException e) {
            return e.message();
        }
    }

    /**
     * Breaks down the command into its description
     * Make the todo task according to the description
     *
     * @param command todo task command input by user
     * @throws TakoException if the todo command is not in the right form
     */
    public static String parseTodo(String command) {
        try {
            String[] commandDetails = command.trim().split(" ",2);
            if (commandDetails.length != 2 || !commandDetails[0].equalsIgnoreCase("todo")) {
                throw new TakoException("todo");
            }
            return TaskList.addTask(new ToDo(commandDetails[1]));
        } catch (TakoException e) {
            return e.message();
        }
    }

    /**
     * Breaks down the command into its description and when it is due
     * Make the deadline task according to the description and its due date
     *
     * @param command deadline task command input by user
     * @throws TakoException if the deadline command is not in the right form
     */
    public static String parseDeadline(String command) {
        try {
            String[] commandDetails = command.trim().split(" ",2);
            if (commandDetails.length != 2 || !commandDetails[0].equalsIgnoreCase("deadline")) {
                throw new TakoException("deadline");
            }
            String[] commandDescriptionDate = commandDetails[1].trim().split("/by", 2);
            if (commandDescriptionDate.length != 2) {
                throw new TakoException("deadline");
            }
            String commandDescription = commandDescriptionDate[0].trim();
            String commandDate = commandDescriptionDate[1].trim();
            if (!checkValidDate(commandDate)) {
                throw new TakoException("deadline");
            }
            return TaskList.addTask(new Deadline(commandDescription, LocalDate.parse(commandDate)));
        } catch (TakoException e) {
            return e.message();
        }
    }

    /**
     * Breaks down the command into its description, starting date and end date
     * Make the event task according to the description, start and end date
     *
     * @param command event command input by user
     * @throws TakoException if the event command is not in the right form
     */
    public static String parseEvent(String command) {
        try {
            String[] commandDetails = command.trim().split(" ",2);
            if (commandDetails.length != 2 || !commandDetails[0].equalsIgnoreCase("event")) {
                throw new TakoException("event");
            }
            String[] commandDescriptionDate = commandDetails[1].trim().split("/from", 2);
            if (commandDescriptionDate.length != 2) {
                throw new TakoException("event");
            }
            String commandDescription = commandDescriptionDate[0].trim();
            String[] fromToDate = commandDescriptionDate[1].split("/to");
            String fromDate = fromToDate[0].trim();
            String toDate = fromToDate[1].trim();
            if (!checkValidDate(fromDate) || !checkValidDate(toDate)) {
                throw new TakoException("event");
            }
            return TaskList.addTask(new Event(commandDescription,
                                    LocalDate.parse(fromDate), LocalDate.parse(toDate)));
        } catch (TakoException e) {
            return e.message();
        }
    }


    /**
     * Checks whether the input is of the correct format and if so,
     * whether it is an existing date
     *
     *
     * @param date the date to check whether it is valid
     * @return boolean of whether the date is valid
     */
    public static boolean checkValidDate(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Changes the date from MONTH DD YYYY to a local date class
     *
     * @param date the date to be changed
     * @return LocalDate object
     */
    public static LocalDate changeDateToLocalDate(String date) {

        String[] dates = date.split(" ");
        assert(dates.length == 3);
        int intMonth = Month.valueOf(dates[0]).getValue();
        String month;
        if (intMonth < 10) {
            month = "0" + intMonth;
        } else {
            month = Integer.toString(intMonth);
        }
        String day = dates[1];
        if (day.length() == 1) {
            day = "0" + day;
        }
        String year = dates[2];
        return LocalDate.parse(year + "-" + month + "-" + day);
    }
}