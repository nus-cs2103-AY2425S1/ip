package duck.data.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An interface that provides methods for formatting date and time representations.
 * Classes implementing this interface can use the provided default methods to
 * format {@link LocalDateTime} objects for file storage and user display.
 */
public interface Datable {

    /**
     * Formats the given {@link LocalDateTime} object into a string suitable for file storage.
     * The format used is "yyyy-MM-dd HHmm".
     *
     * @param date The {@link LocalDateTime} object to format.
     * @return A string representation of the date and time in the format "yyyy-MM-dd HHmm".
     */
    default String getFileDateString(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Formats the given {@link LocalDateTime} object into a string suitable for user display.
     * The format used is "MMM dd yyyy HH:mm".
     *
     * @param date The {@link LocalDateTime} object to format.
     * @return A string representation of the date and time in the format "MMM dd yyyy HH:mm".
     */
    default String getDisplayDateString(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }
}
