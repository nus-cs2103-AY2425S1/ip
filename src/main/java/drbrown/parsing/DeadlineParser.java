package drbrown.parsing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import drbrown.command.AddCommand;
import drbrown.command.Command;
import drbrown.task.Deadline;
import drbrown.task.Task;
import drbrown.utils.DrBrownException;
import drbrown.utils.Ui;

/**
 * A parser that handles the "deadline" command input.
 * Responsible for parsing the input to create a {@link Deadline} task
 * with a description and a date.
 */
public class DeadlineParser extends Parsing {

    /** The formatter for parsing date and time in the specific format "MMM dd yyyy HH:mm". */
    static final DateTimeFormatter FILE_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public DeadlineParser(String[] inputSplit) {
        super(inputSplit);
    }

    /**
     * Parses the input to create an {@link AddCommand} that adds a {@link Deadline} task.
     *
     * @return An instance of {@link AddCommand} containing the {@link Deadline} task.
     * @throws DrBrownException If the input is invalid, such as missing description or date,
     *                          or if the date format is incorrect.
     */
    public Command parse() throws DrBrownException {
        assert this.getInputSplit() != null : "Input string array should not be null";
        try {
            if (this.getInputSplit().length == 1) {
                throw new DrBrownException(Ui.getDeadlineExceptionNoDescription());
            }

            String[] deadlineSplit = this.getInputSplit()[1].split("/by | /priority");
            if (deadlineSplit.length == 1) {
                throw new DrBrownException(Ui.getDeadlineExceptionNoDate());
            }

            Task deadline = new Deadline(false, deadlineSplit[0].trim(),
                    LocalDateTime.parse(deadlineSplit[1].trim(), FILE_DATE_TIME_FORMATTER),
                    Task.Priority.valueOf(deadlineSplit[2].trim()));

            return new AddCommand(deadline);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException | IllegalArgumentException e) {
            throw new DrBrownException(Ui.getDeadlineExceptionOthers());
        }
    }

}
