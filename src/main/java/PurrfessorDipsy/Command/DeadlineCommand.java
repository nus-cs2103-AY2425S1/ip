package PurrfessorDipsy.Command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import PurrfessorDipsy.Exception.InvalidCommandException;
import PurrfessorDipsy.Exception.InvalidDateException;
import PurrfessorDipsy.Parser.DateTimeParser;
import PurrfessorDipsy.Task.Deadline;
import PurrfessorDipsy.TaskList.TaskList;
import PurrfessorDipsy.Ui.Ui;

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
                LocalDate parsedBy = DateTimeParser.parseDate(by);
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
