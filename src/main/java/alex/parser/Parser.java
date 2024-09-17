package alex.parser;

import java.time.LocalDate;

/**
 * The parser class makes sense of user inputs
 */

public class Parser {

    /**
     * extracts the index part of the string of mark commands
     *
     * @param input entered by user
     * @return index of item to be marked
     */
    public int extractIndexForMark(String input) {
        return Integer.parseInt(input.substring(5)) - 1;
    }

    /**
     * extracts the index part of the string of unmark commands
     *
     * @param input entered by user
     * @return index of item to be unmarked
     */
    public int extractIndexForUnmark(String input) {
        return Integer.parseInt(input.substring(7)) - 1;
    }
    /**
     * extracts the description part of the string for to-do commands
     *
     * @param input entered by user
     * @return description of to-do
     */
    public String extractDescriptionForTodo(String input) {
        return input.substring(4).trim();
    }
    /**
     * extracts the description part of the string for deadline commands
     *
     * @param input entered by user
     * @return description of deadline
     */
    public String extractDescriptionForDeadline(String input) {
        return input.substring(8).trim();
    }
    /**
     * splits the input into an array of strings where there are "/"
     *
     * @param input entered by user
     * @return array of strings
     */
    public String[] parseIntoArrayOfDetailsForDeadline(String input) {
        return input.split("/", 2);
    }
    /**
     * extracts the description part of the string for event commands
     *
     * @param input entered by user
     * @return description of event
     */
    public String extractDescriptionForEvent(String input) {
        return input.substring(5).trim();
    }
    /**
     * splits the input into an array of strings where there are "/"
     *
     * @param input entered by user
     * @return array of strings
     */
    public String[] parseIntoArrayOfDetailsForEvent(String input) {
        return input.split("/", 3);
    }
    /**
     * extracts the name of the task
     *
     * @param input entered by user
     * @return string name of task
     */
    public String extractNameOfTask(String input) {
        return input.trim();
    }
    /**
     * extracts the date part of the string
     *
     * @param input entered by user
     * @return date that the task begins
     */
    public LocalDate extractStartDate(String input) {
        String intermediate = input.substring(4).trim();
        return parseStringToDate(intermediate);
    }
    /**
     * extracts the date part of the string
     *
     * @param input entered by user
     * @return date that the task ends
     */
    public LocalDate extractEndDate(String input) {
        String intermediate = input.substring(2).trim();
        return parseStringToDate(intermediate);
    }
    /**
     * converts an object of type string to type LocalDate
     *
     * @param input entered by user
     * @return LocalDate
     */
    public LocalDate parseStringToDate(String input) {
        return LocalDate.parse(input);
    }
    /**
     * extracts the index part of the string for delete commands
     *
     * @param input entered by user
     * @return index of item to be deleted
     */
    public int extractIndexForDelete(String input) {
        return Integer.parseInt(input.substring(7)) - 1;
    }
    /**
     * extracts the date part of the string
     *
     * @param input entered by user
     * @return date to be found in the list of tasks
     */
    public LocalDate extractDateForDateCommand(String input) {
        return LocalDate.parse(input.substring(9).trim());
    }
    /**
     * extracts the keyword part of the string for find commands
     *
     * @param input entered by user
     * @return keyword to be found
     */
    public String extractKeywordForFind(String input) {
        return input.substring(5).trim();
    }
    /**
     * extracts the index part of the string to be tagged
     *
     * @param input entered by user
     * @return index of item to be tagged
     */
    public int extractIndexForTag(String input) {
        return Integer.parseInt(input.substring(4,5)) - 1;
    }
    /**
     * extracts the label part of the string for tagging commands
     *
     * @param input entered by user
     * @return label to tag the task with
     */
    public String extractLabelForTag(String input) {
        return input.substring(5).trim();
    }
    /**
     * extracts the details of the task from the saved file
     *
     * @param input the string that was saved in the file
     * @return string details of task
     */
    public String extractDetailsFromSavedFile(String input) {
        return input.substring(6).trim();
    }
    /**
     * splits the input into an array of strings where there are "//"
     *
     * @param input string that was saved in the file
     * @return array of strings
     */
    public String[] parseIntoArrayOfInfoFromSavedFile(String input) {
        return input.split("//");
    }
    /**
     * extracts the date part of the string from the saved file
     *
     * @param input the date that the task begins in String
     * @return date that the task begins in LocalDate
     */
    public LocalDate extractStartDateFromSavedFile(String input) {
        String intermediate = input.substring(6).trim();
        return parseStringToDate(intermediate);
    }
    /**
     * extracts the date part of the string from the saved file
     *
     * @param input the date that the task ends in String
     * @return date that the task ends in LocalDate
     */
    public LocalDate extractEndDateFromSavedFile(String input) {
        String intermediate = input.substring(4).trim();
        return parseStringToDate(intermediate);
    }
    /**
     * extracts the status icon of the task from the saved file
     *
     * @param input the date that the task ends in String
     * @return status icon of the task, either "X" or " "
     */
    public String extractStatusOfTaskFromSavedFile(String input) {
        return input.substring(4,5);
    }
}