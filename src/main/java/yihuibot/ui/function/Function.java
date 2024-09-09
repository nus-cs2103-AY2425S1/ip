package yihuibot.ui.function;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import yihuibot.exception.parse.ParseException;
import yihuibot.executable.Executable;

/**
 * A function to process the arguments and return an appropriate Executable.
 *
 * @author Toh Yi Hui A0259080A
 */
public abstract class Function {
    /**
     * Calls the function with the given arguments to generate an Executable.
     *
     * @param arguments the list of arguments.
     * @return an appropriate Executable.
     * @throws ParseException when function cannot be called with arguments.
     */
    public abstract Executable call(String... arguments) throws ParseException;

    /**
     * Find and return the index of String s in a String array.
     *
     * @param array the string array to be searched.
     * @param s the string to search for.
     * @return the index of the String s in given array.
     *         Return -1 if no such String is found.
     */
    protected int findIndexOfStringInArray(String[] array, String s) {
        return Arrays.<String>asList(array).indexOf(s);
    }

    /**
     * Slice the array with range from and to, and then join the remaining array using
     * the given delimiter.
     *
     * @param array the string array to slice.
     * @param from the index of the array to slice from, inclusive.
     * @param to the index of the array to slice to, exclusive.
     * @param delimiter the delimiter that separates each element.
     * @return the resulting String after joining it using delimiter.
     */
    protected String sliceAndJoinAt(String[] array, int from, int to, CharSequence delimiter) {
        return String.join(delimiter, Arrays.<String>copyOfRange(array, from, to));
    }

    /**
     * Converts the String into a LocalDateTime object.
     *
     * @param dateTime the dateTime in String.
     * @return a LocalDateTime object.
     * @throws DateTimeParseException when the string cannot be parsed.
     */
    protected LocalDateTime parseDateTime(String dateTime, DateTimeFormatter formatter)
            throws DateTimeParseException {
        return LocalDateTime.parse(dateTime, formatter);
    }
}
