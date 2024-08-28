package purrfessordipsy.command;

import purrfessordipsy.exception.InvalidCommandException;
import purrfessordipsy.exception.InvalidDateException;
import purrfessordipsy.parser.DateParser;
import purrfessordipsy.task.Event;
import purrfessordipsy.tasklist.TaskList;
import purrfessordipsy.ui.Ui;
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
                LocalDate parsedStart = DateParser.parseDate(start);
                LocalDate parsedEnd = DateParser.parseDate(end);
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
