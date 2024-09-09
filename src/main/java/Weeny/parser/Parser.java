package weeny.parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses and converts date and time information from strings.
 */
public class Parser {

    /**
     * Converts a date string in the format "dd/MM/yyyy" to a LocalDate object.
     *
     * @param date Date string to convert.
     * @return The corresponding LocalDate object.
     */
    public LocalDate convertDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Invalid date format: " + date);
            throw new IllegalArgumentException("Date must be in the format dd/MM/yyyy", e);
        }
    }

    /**
     * Converts a time string in the format "HHmm" to a LocalTime object.
     *
     * @param time Time string to convert.
     * @return The corresponding LocalTime object.
     */
    public LocalTime convertTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        try {
            return LocalTime.parse(time, formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Invalid time format: " + time);
            throw new IllegalArgumentException("Time must be in the format HHmm", e);
        }
    }

    /**
     * Extracts the first word from a sentence.
     *
     * @param sentence The input sentence.
     * @return The first word.
     */
    public String extractFirstWord(String sentence) {
        int spaceIndex = sentence.indexOf(" ");
        return spaceIndex == -1 ? sentence : sentence.substring(0, spaceIndex);
    }

    /**
     * Extracts the end number from a sentence.
     *
     * @param sentence The input sentence.
     * @return The end number.
     */
    public int extractEndNumber(String sentence) {
        int spaceIndex = sentence.indexOf(" ");
        return Integer.parseInt(sentence.substring(spaceIndex + 1).trim());
    }

    /**
     * Extracts the deadline time from a sentence.
     *
     * @param sentence The input sentence.
     * @return The deadline time string.
     */
    public String extractDeadlineTime(String sentence) {
        int index = sentence.indexOf("/by") + 4;
        return sentence.substring(index).trim();
    }

    /**
     * Extracts the start and end times of an event from a sentence.
     *
     * @param sentence The input sentence.
     * @return An array with the start and end times.
     */
    public String[] extractEventTimes(String sentence) {
        int fromIndex = sentence.indexOf("/from") + 6;
        int toIndex = sentence.indexOf("/to");
        int toIndexPlus4 = toIndex + 4;
        return new String[]{
                sentence.substring(fromIndex, toIndex - 1).trim(),
                sentence.substring(toIndexPlus4).trim()
        };
    }

    /**
     * Extracts the event name from a sentence.
     *
     * @param input The input sentence.
     * @return The event name.
     */
    public String extractEventName(String input) {
        return input.substring(6, input.indexOf("/from") - 1).trim();
    }

    /**
     * Extracts the deadline name from a sentence.
     *
     * @param input The input sentence.
     * @return The deadline name.
     */
    public String extractDeadlineName(String input) {
        return input.substring(9, input.indexOf("/by") - 1).trim();
    }
}
