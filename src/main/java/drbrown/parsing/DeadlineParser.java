package drbrown.parsing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import drbrown.command.AddCommand;
import drbrown.command.Command;
import drbrown.task.Deadline;
import drbrown.task.Task;
import drbrown.utils.DrBrownException;

/**
 * A parser that handles the "deadline" command input.
 * Responsible for parsing the input to create a {@link Deadline} task
 * with a description and a date.
 */
public class DeadlineParser {

    /** The formatter for parsing date and time in the specific format "MMM dd yyyy HH:mm". */
    static final DateTimeFormatter FILE_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Parses the input to create an {@link AddCommand} that adds a {@link Deadline} task.
     *
     * @param inputSplit An array of strings containing the user's input split by spaces.
     * @return An instance of {@link AddCommand} containing the {@link Deadline} task.
     * @throws DrBrownException If the input is invalid, such as missing description or date,
     *                          or if the date format is incorrect.
     */
    public static Command parse(String[] inputSplit) throws DrBrownException {
        assert inputSplit != null : "Input string array should not be null";
        try {
            if (inputSplit.length == 1) {
                throw new DrBrownException("Great Scott! You can't add a deadline without a "
                        + "description and date!\nUse the format: deadline {description} /by {date}");
            }
            String[] deadlineSplit = inputSplit[1].split("/by");
            if (deadlineSplit[0].isEmpty()) {
                throw new DrBrownException("Hello? Hello? Anybody home? Looks like something's missing "
                        + "here!\nUse the format: deadline {description} /by {date}");
            }
            Task deadline = new Deadline(false, deadlineSplit[0].trim(),
                    LocalDateTime.parse(deadlineSplit[1].trim(), FILE_DATE_TIME_FORMATTER));
            return new AddCommand(deadline);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new DrBrownException("Looks like your Uncle Joey didn't make parole again... "
                    + "and you missed the date! Let's fix that deadline!\nUse the format: deadline "
                    + "{description} /by {MMM dd yyyy HH:mm}");
        }
    }

}
