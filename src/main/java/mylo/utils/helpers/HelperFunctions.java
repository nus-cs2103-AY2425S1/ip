package mylo.utils.helpers;

import java.time.LocalDateTime;

import mylo.utils.exceptions.IllegalValueException;

/**
 * Utility class for helper functions related to date and time manipulation.
 * <p></p>
 * <p>This class provides methods to convert strings into {@code LocalDateTime} objects
 * with specified formats.</p>
 *
 * @author cweijin
 *
 */
public class HelperFunctions {
    /**
     * Converts a string representation of date and time into a {@code LocalDateTime} object.
     *
     * @param str The string representation of date and time in the format "dd-mm-yyyy hhmm".
     * @return A {@code LocalDateTime} object representing the parsed date and time.
     * @throws IllegalValueException if the provided string is not in the correct format.
     */
    public static LocalDateTime stringToDateTime(String str) throws IllegalValueException {
        return stringToDateTime(str, false);
    }

    /**
     * Converts a string representation of date into a {@code LocalDateTime} object.
     *
     * @param str The string representation of date and time in the format "dd-mm-yyyy hhmm".
     * @param isDateOnly A boolean indicating whether to ignore the time component.
     * @return A {@code LocalDateTime} object representing the parsed date and time.
     *         If {@code dateOnly} is true, the time will be set to 00:00.
     * @throws IllegalValueException if the provided string is not in the correct format.
     */
    public static LocalDateTime stringToDateTime(String str, boolean isDateOnly) throws IllegalValueException {
        try {
            String[] dateTime = str.split(" ");

            String[] date = dateTime[0].split("-");
            int year = Integer.parseInt(date[2]);
            int month = Integer.parseInt(date[1]);
            int day = Integer.parseInt(date[0]);

            if (!isDateOnly) {
                String time = dateTime[1];
                int hour = Integer.parseInt(time.substring(0, 2));
                int minute = Integer.parseInt(time.substring(2, 4));

                return LocalDateTime.of(year, month, day, hour, minute);
            }

            return LocalDateTime.of(year, month, day, 0, 0);
        } catch (Exception e) {
            throw new IllegalValueException("Date and time provided is not of correct format "
                    + "(dd-mm-yyyy hhmm): " + str);
        }
    }
}
