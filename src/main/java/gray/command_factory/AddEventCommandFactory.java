package gray.command_factory;

import gray.GrayException;
import gray.command.AddEventCommand;
import gray.command.Command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddEventCommandFactory extends CommandFactory {
    @Override
    public Command parse(String text) throws GrayException {
        Pattern pattern = Pattern.compile("event (.*) /from (.*) /to (.*)");
        Matcher matcher = pattern.matcher(text);
        if (!matcher.matches()) return null;
        String description = matcher.group(1);
        String start = matcher.group(2);
        String end = matcher.group(3);
        if (description.isEmpty() || start.isEmpty() || end.isEmpty()) {
            throw new GrayException("OOPS!!! The description, start and end cannot be empty.");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime startDate = LocalDateTime.parse(start, formatter);
        LocalDateTime endDate = LocalDateTime.parse(end, formatter);
        return new AddEventCommand(description, startDate, endDate);
    }
}
