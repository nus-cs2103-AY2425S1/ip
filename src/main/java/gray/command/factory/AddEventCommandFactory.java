package gray.command.factory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gray.GrayException;
import gray.command.AddEventCommand;
import gray.command.Command;

/**
 * A parser that constructs a command to add event task.
 */
public class AddEventCommandFactory extends CommandFactory {

    /**
     * Returns the add event command parsed from the text.
     *
     * @param text
     * @return Add event task command
     * @throws GrayException
     */
    @Override
    public Command parse(String text) throws GrayException {
        Pattern pattern = Pattern.compile("event (.*) /from (.*) /to (.*)");
        Matcher matcher = pattern.matcher(text);
        if (!matcher.matches()) {
            return null;
        }
        String description = matcher.group(1);
        String start = matcher.group(2);
        String end = matcher.group(3);
        if (description.isEmpty() || start.isEmpty() || end.isEmpty()) {
            throw new GrayException("OOPS!!! The description, start and end cannot be empty.");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime startDate;
        LocalDateTime endDate;
        try {
            startDate = LocalDateTime.parse(start, formatter);
            endDate = LocalDateTime.parse(end, formatter);
        } catch (Exception e) {
            throw new GrayException("Cannot parse datetime yyyy-MM-dd HHmm");
        }
        return new AddEventCommand(description, startDate, endDate);
    }
}
