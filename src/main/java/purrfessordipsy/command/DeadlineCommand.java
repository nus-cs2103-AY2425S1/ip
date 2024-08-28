package purrfessordipsy.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import purrfessordipsy.exception.InvalidCommandException;
import purrfessordipsy.exception.InvalidDateException;
import purrfessordipsy.parser.DateParser;
import purrfessordipsy.task.Deadline;
import purrfessordipsy.tasklist.TaskList;
import purrfessordipsy.ui.Ui;

public class DeadlineCommand extends Command {
    private static final Pattern DEADLINE_PATTERN = Pattern.compile("^deadline (.+) /by (.+)$");

    public DeadlineCommand(String userInput, TaskList tasks, Ui ui) {
        super(userInput, tasks, ui);
    }

    @Override
    public void execute() throws InvalidCommandException, InvalidDateException {
        Matcher matcher = DEADLINE_PATTERN.matcher(userInput);
        if (matcher.matches()) {
            String description = matcher.group(1);
            String by = matcher.group(2);
            try {
                LocalDate parsedBy = DateParser.parseDate(by);
                Deadline deadline = new Deadline(description, parsedBy);
                tasks.addTask(deadline);
                ui.printTaskAddedMessage(deadline, tasks.getSize());
            } catch (DateTimeParseException e) {
                throw new InvalidDateException();
            }
        } else {
            throw new InvalidCommandException(InvalidCommandException.ErrorType.INVALID_DEADLINE);
        }
    }
}
