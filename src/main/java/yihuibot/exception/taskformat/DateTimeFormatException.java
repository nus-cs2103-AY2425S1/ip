package yihuibot.exception.taskformat;

import java.time.format.DateTimeFormatter;

/**
 * An Exception for when given String cannot be converted into a LocalDateTime object
 * using the given formatter.
 *
 * @author Toh Yi Hui A0259080A
 */
public class DateTimeFormatException extends IncorrectTaskFormatException {
    /**
     * Constructor for a new DateTimeFormatException.
     *
     * @param input the input that was rejected by the formatter.
     * @param formatter the DateTimeFormatter used to format the String.
     */
    public DateTimeFormatException(String input, DateTimeFormatter formatter) {
        super(input + " cannot be parsed by formatter: " + formatter);
    }
}
