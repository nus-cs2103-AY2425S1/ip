package tars.parsers;

import tars.exceptions.TarsException;

import tars.tasks.Task;
import tars.tasks.Deadline;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Parses input data to create a {@link Deadline} task.
 *
 * <p>The {@code DeadlineParser} class extends the {@link Parser} class and provides
 * specific logic to parse user input and create {@link Deadline} tasks. It ensures that
 * the input format is correct and throws custom exceptions in case of errors.</p>
 */
public class DeadlineParser extends Parser {

    /**
     * Parses input data to create a {@link Deadline} task.
     *
     * <p>The {@code DeadlineParser} class extends the {@link Parser} class and provides
     * specific logic to parse user input and create {@link Deadline} tasks. It ensures that
     * the input format is correct and throws custom exceptions in case of errors.</p>
     */
    @Override
    public Task parse(String[] taskInfo) {
        if (taskInfo.length <= 1) {
            throw new TarsException("Add a name to your deadline");
        }

        String[] taskDetails = taskInfo[1].split("/", 2);

        String name = taskDetails[0].trim();
        String[] date = taskDetails.length > 1
                ? taskDetails[1].split(" ", 2)
                : null;

        if (name.isEmpty()) {
            throw new TarsException("Try again. Next time tell me what your deadline is");
        }

        if (date == null) {
            throw new TarsException("Add a /by command and a deadline date");
        }

        LocalDate deadlineDate = validateCommand(date);

        return new Deadline(name, deadlineDate);
    }

    /**
     * Validates the deadline command to ensure it contains a valid "/by" command and a properly formatted date.
     *
     * @param deadlineCommand An array of strings representing the deadline command. The first element is expected
     *                        to be the "/by" keyword, and the second element should be the date in "dd-MM-yy" format.
     * @return A {@link LocalDate} representing the parsed and validated deadline date.
     * @throws TarsException if the command is missing or the date is improperly formatted.
     */
    public LocalDate validateCommand(String[] deadlineCommand) {

        CommandValidator.validate(deadlineCommand, CommandValidator.CommandType.BY);

        LocalDate deadlineDate;
        try {
            deadlineDate = LocalDate.parse(deadlineCommand[1], FORMATTER);
        } catch (DateTimeParseException e) {
            throw new TarsException("Date in wrong format. It should be in dd-MM-yy format");
        }

        return deadlineDate;
    }

}

