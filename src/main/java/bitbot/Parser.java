package bitbot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parser class helps to parse the input and extract out the necessary data such as
 * description, from time, to time and by time.
 */
public class Parser {
    static final String FROM_TO_TIME_MISSING = "OOPS!!! The description and timings of an event "
            + "should not be empty.\n"
            + "          Please add a description to the event you wish to add to the list.\n"
            + "          For example: \"event return book /from Mon 4pm /to 6pm\"\n"
            + "          Or \"event return book /from 29-04-2021 18:00 /to 29-04-2021 18:30\"";

    static final String BY_TIME_MISSING = "OOPS!!! You need to add the \"by\" details.\n"
            + "          For example: deadline homework /by Aug 6th";

    static final String TODO_DESCRIPTION_MISSING = "OOPS!!! Need to add a description for a todo activity\n "
            + "         For example: todo borrow book";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Throws new BitBotException if the length of input is less than 2.
     *
     * @param message the message that needs to be displayed
     * @param partsOfInput the String[] of the split input
     * @throws BitBotException if the length of the input is less than 2
     */
    static void handleErrorForWrongLength(String message, String[] partsOfInput) throws BitBotException {
        if (partsOfInput.length < 2) {
            throw new BitBotException(message);
        }
    }

    /**
     * Extracts out the description from the input
     *
     * @param partsOfInput the String[] of the split input
     * @param stopKeyword the keyword that is used to signify when the description ends
     * @return a String
     */
    static String extractDescription(String[] partsOfInput, String stopKeyword) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < partsOfInput.length; i++) {
            if (!partsOfInput[i].equals(stopKeyword)) {
                sb.append(partsOfInput[i]).append(" ");
            } else {
                break;
            }
        }
        return sb.toString().trim();
    }

    /**
     * Extracts the time detail from the user input
     *
     * @param partsOfInput the String[] of the split input
     * @param startKeyword the keyword from which the time String starts
     * @param endKeyword the keyword from which the time String ends
     * @return a String
     */
    static String extractTimeDetail(String[] partsOfInput, String startKeyword, String endKeyword) {
        StringBuilder timeDetail = new StringBuilder();
        boolean isStartKeywordFound = false;

        for (String part : partsOfInput) {
            if (isStartKeywordFound) {
                if (endKeyword != null && part.equals(endKeyword)) {
                    break;
                }
                if (timeDetail.length() > 0) {
                    timeDetail.append(" ");
                }
                timeDetail.append(part);

            }
            if (part.equals(startKeyword)) {
                isStartKeywordFound = true;
            }

        }
        return timeDetail.toString().trim();
    }

    /**
     * Parses the input into a Date and Time format
     *
     * @param dateTimeString the time String inputted
     * @return LocalDateTime
     */
    static LocalDateTime parseDateTime(String dateTimeString) {
        try {
            return LocalDateTime.parse(dateTimeString, Parser.DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Parses the input into a Time format
     *
     * @param timeString the time String inputted
     * @return LocalTime
     */
    static LocalTime parseTime(String timeString) {
        try {
            return LocalTime.parse(timeString, Parser.TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Parses the input into a Date format
     *
     * @param dateString the time String inputted
     * @return LocalDate
     */
    static LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString, Parser.DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Builds the return message that is displayed once the user keys in a successful input
     *
     * @param taskType the type of Task
     * @param length the length of the arrayList
     * @param task whether it is "task" or "tasks"
     * @return a String
     */
    static String buildTaskMessage(Task taskType, int length, String task) {
        return "          ____________________________________\n          Got it. I've added this task:\n"
                + "             " + taskType.finalString() + "\n"
                + "          Now you have " + length + " " + task + " in the list.\n"
                + "          ____________________________________";
    }

}
