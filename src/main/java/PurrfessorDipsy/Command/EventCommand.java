package PurrfessorDipsy.Command;

import PurrfessorDipsy.Exception.InvalidCommandException;
import PurrfessorDipsy.Exception.InvalidDateException;
import PurrfessorDipsy.Parser.DateTimeParser;
import PurrfessorDipsy.Task.Event;
import PurrfessorDipsy.TaskList.TaskList;
import PurrfessorDipsy.Ui.Ui;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventCommand extends Command {
    private static final Pattern EVENT_PATTERN = Pattern.compile("^event (.+) /from (.+) /to (.+)$");

    public EventCommand(String userInput, TaskList tasks, Ui ui) {
        super(userInput, tasks, ui);
    }

    @Override
    public void execute() throws InvalidDateException, InvalidCommandException {
        Matcher matcher = EVENT_PATTERN.matcher(userInput);
        if (matcher.matches()) {
            String description = matcher.group(1);
            String start = matcher.group(2);
            String end = matcher.group(3);
            try {
                LocalDate parsedStart = DateTimeParser.parseDate(start);
                LocalDate parsedEnd = DateTimeParser.parseDate(end);
                Event event = new Event(description, parsedStart, parsedEnd);
                tasks.addTask(event);
                ui.printTaskAddedMessage(event, tasks.getSize());
            } catch (DateTimeParseException e) {
                throw new InvalidDateException();
            }
        } else {
            throw new InvalidCommandException(InvalidCommandException.ErrorType.INVALID_EVENT);
        }
    }
}
