package bitbot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.stream.Collectors;

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

    static final String LENGTH_OF_INPUT_TOO_SMALL = "OOPS!! Add a string of words you want to find.\n"
            + "          Please do not leave it blank.";
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

    static void handleErrorForNoFurtherInput(String... inputString) throws BitBotException {
        if (inputString == null || inputString.length == 0) {
            throw new BitBotException(LENGTH_OF_INPUT_TOO_SMALL);
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
        return Arrays.stream(partsOfInput) // converts into a stream
                .skip(1) //skips the first word as that would be the keyword (e.g. find, event, deadline, todo)
                .takeWhile(part -> !part.equals(stopKeyword)) // takes the element
                // as long as they do not match stopKeyword
                .collect(Collectors.joining(" ")); // collects the element of a stream into a String with a space
    }

    /**
     * Extracts the time detail from the user input
     *
     * @param partsOfInput the String[] of the split input
     * @param startKeyword the keyword from which the time String starts
     * @param endKeyword the keyword from which the time String ends
     * @return a String
     */
    // for the part on Collectors.joining(" ") I had to refer to the javadocs and the example from
    // https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html
    static String extractTimeDetail(String[] partsOfInput, String startKeyword, String endKeyword) {
        return Arrays.stream(partsOfInput) // converts into a stream
                .dropWhile(part -> !part.equals(startKeyword)) // skips over the elements until the startKeyword
                .skip(1) // skips the word startKeyword
                .takeWhile(part -> endKeyword == null || !part.equals(endKeyword)) // takes the element as long as
                // they do not match endKeyword
                .collect(Collectors.joining(" ")); // collects the element of a stream into a String with a space
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
