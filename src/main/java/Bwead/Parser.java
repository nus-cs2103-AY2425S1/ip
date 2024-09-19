package Bwead;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Takes in a command, and provides necessary information about the command
 * back to the Ui.
 */
public class Parser {

    private static Scanner scanner = new Scanner(System.in);
    private String command;

    /**
     * Constructor for Parser, creating a command to parse.
     *
     * @param command input command.
     */
    public Parser(String command) {
        this.command = command;
    }

    /**
     * Checks if the command is a single word.
     *
     * @return boolean true if the command is a single word.
     * @throws IOException when a failure occurs while performing write operations.
     */
    public boolean isOneWord() {
        return command.indexOf(" ") == -1;
    }

    /**
     * Checks if the command is more than one word and invalid.
     *
     * @return boolean true if the command is invalid and multiple words.
     */
    public boolean isInvalidMultiWord() {
        return !command.startsWith("todo") && !command.startsWith("deadline") && !command.startsWith("event")
                && !command.startsWith("delete") && !command.startsWith("mark") && !command.startsWith("unmark")
                && !command.startsWith("find") && !command.startsWith("snooze");
    }

    /**
     * Gets the index of the task to mark.
     *
     * @return int index of task in taskList.
     */
    public int getTaskToMark() {
        return Integer.valueOf(command.split(" ")[1]);
    }

    /**
     * Gets the Todo task name.
     *
     * @return String Todo task name.
     */
    public String getTodoName() {
        return command.replace("todo ", "");
    }

    /**
     * Gets the Deadline task name.
     *
     * @return String Deadline task name.
     */
    public String getDeadlineName() {
        String input1 = command.replace("deadline ", "");
        int slash = input1.indexOf("/");
        return input1.substring(0, slash - 1);
    }

    /**
     * Gets the Deadline task date.
     *
     * @return LocalDate Deadline task date.
     * @throws BweadException if date input is invalid.
     */
    public LocalDate getDeadlineDate() throws BweadException {
        try {
            String dateString = command.split("/by ")[1];
            dateString = dateString.substring(0, dateString.length() - 5);
            LocalDate date = LocalDate.parse(dateString);
            return date;
        } catch (DateTimeParseException e) {
            throw new BweadException("invalid date");
        }
    }

    /**
     * Gets the Event task name.
     *
     * @return String Event task name
     */
    public String getEventName() {
        String input = command.replace("event ", "");
        return input.split("/from")[0];
    }

    /**
     * Gets the Event task start date.
     *
     * @return LocalDate event task start date.
     * @throws BweadException if date input is invalid.
     */
    public LocalDate getEventStart() throws BweadException {
        try {
            String startString = command.split("/from ")[1].split(" /to")[0];
            LocalDate start = LocalDate.parse(startString.substring(0, startString.length() - 5));
            return start;
        } catch (DateTimeParseException e) {
            throw new BweadException("invalid date");
        }
    }

    /**
     * Gets the EVent task end date.
     *
     * @return LocalDate Event task end date.
     * @throws BweadException if date input is invalid.
     */
    public LocalDate getEventEnd() throws BweadException {
        try {
            String endString = command.split("/from ")[1].split("/to ")[1];
            LocalDate end = LocalDate.parse(endString.substring(0, endString.length() - 5));
            return end;
        } catch (DateTimeParseException e) {
            throw new BweadException("invalid date");
        }
    }

    /**
     * Gets the index of task to delete.
     *
     * @return int task index.
     */
    public int getDeleteIndex() {
        return Integer.valueOf(command.split(" ")[1]);
    }

    public LocalTime getDeadlineTime() throws BweadException {
        try {
            String dateTimeString = command.split("/by ")[1];
            String timeString = dateTimeString.substring(dateTimeString.length() - 4, dateTimeString.length());
            return LocalTime.parse(timeString.substring(0, 2) + ":" + timeString.substring(2, 4));
        } catch (DateTimeParseException e) {
            throw new BweadException("invalid time");
        }
    }

    /**
     * Gets the Event task start time.
     *
     * @return LocalTime Event task start time.
     * @throws BweadException if time input is invalid.
     */
    public LocalTime getEventStartTime() throws BweadException {
        try {
            String startDateTimeString = command.split("from ")[1].split(" /to")[0];
            String startTimeString = startDateTimeString.substring(startDateTimeString.length() - 4,
                    startDateTimeString.length());
            return LocalTime.parse(startTimeString.substring(0, 2) + ":" + startTimeString.substring(2, 4));
        } catch (DateTimeParseException e) {
            throw new BweadException("invalid time");
        }
    }

    /**
     * Gets the Event task end time.
     *
     * @return LocalTime Event task end time.
     * @throws BweadException if time input is invalid.
     */
    public LocalTime getEventEndTime() throws BweadException {
        try {
            String endDateTimeString = command.split("to ")[1];
            String endTimeString = endDateTimeString.substring(endDateTimeString.length() - 4,
                    endDateTimeString.length());
            return LocalTime.parse(endTimeString.substring(0, 2) + ":" + endTimeString.substring(2, 4));
        } catch (DateTimeParseException e) {
            throw new BweadException("invalid time");
        }
    }

    /**
     * Gets the keyword to search for.
     *
     * @return String search keyword.
     */
    public String getKeyword() {
        return command.split("find ")[1];
    }

    /**
     * Gets the deadline task name to be edited.
     *
     * @return String task name.
     */
    public String getDeadlineTaskToEdit() {
        return command.split("snooze deadline ")[1].split(" to")[0];
    }

    /**
     * Gets the new date of a deadline task.
     *
     * @return LocalDate
     */
    public LocalDate getNewDeadlineDate() throws IOException {
        try {
            String dateString = command.split("to ")[1].split(" ")[0];
            LocalDate start = LocalDate.parse(dateString);
            return start;
        } catch (DateTimeParseException e) {
            throw new BweadException("invalid date");
        }
    }

    /**
     * Gets the new deadline time.
     *
     * @return LocalTime.
     */
    public LocalTime getNewDeadlineTime() throws IOException {
        try {
            String timeString = command.split("to ")[1].split(" ")[1];
            return LocalTime.parse(timeString.substring(0, 2) + ":" + timeString.substring(2, 4));
        } catch (DateTimeParseException e) {
            throw new BweadException("invalid time");
        }
    }

    /**
     * Gets the event task name.
     *
     * @return String event name.
     */
    public String getEventTaskToEdit() {
        return command.split("snooze event ")[1].split(" from")[0];
    }

    /**
     * Gets the event start date to update.
     *
     * @return LocalDate.
     */
    public LocalDate getNewEventStartDate() throws IOException {
        try {
            String startString = command.split("from ")[1].split(" to")[0].split(" ")[0];
            LocalDate start = LocalDate.parse(startString);
            return start;
        } catch (DateTimeParseException e) {
            throw new BweadException("invalid date");
        }
    }

    /**
     * Gets the new event end date.
     *
     * @return LocalDate.
     */
    public LocalDate getNewEventEndDate() throws IOException {
        try {
            String startString = command.split("to ")[1].split(" ")[0];
            LocalDate start = LocalDate.parse(startString);
            return start;
        } catch (DateTimeParseException e) {
            throw new BweadException("invalid date");
        }
    }

    /**
     * Gets the new event start time.
     *
     * @return LocalTime.
     */
    public LocalTime getNewEventStartTime() throws IOException {
        try {
            String timeString = command.split("from ")[1].split("to")[0].split(" ")[1];
            return LocalTime.parse(timeString.substring(0, 2) + ":" + timeString.substring(2, 4));
        } catch (DateTimeParseException e) {
            throw new BweadException("invalid time");
        }
    }

    /**
     * Gets the new event end time.
     *
     * @return LocalTime.
     */
    public LocalTime getNewEventEndTime() throws IOException {
        try {
            String timeString = command.split("to ")[1].split(" ")[1];
            return LocalTime.parse(timeString.substring(0, 2) + ":" + timeString.substring(2, 4));
        } catch (DateTimeParseException e) {
            throw new BweadException("invalid time");
        }
    }
}
