package gray.command.factory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gray.GrayException;
import gray.command.AddDeadlineCommand;
import gray.command.Command;

/**
 * A parser that constructs a command to add deadline task.
 */
public class AddDeadlineCommandFactory extends CommandFactory {

    /**
     * Returns the add deadline command parsed from the text.
     *
     * @param text
     * @return Add deadline task command
     * @throws GrayException
     */
    @Override
    public Command parse(String text) throws GrayException {
        Pattern pattern = Pattern.compile("deadline (.*) /by (.*)");
        Matcher matcher = pattern.matcher(text);
        if (!matcher.matches()) {
            return null;
        }
        String description = matcher.group(1);
        String deadline = matcher.group(2);
        if (description.isEmpty() || deadline.isEmpty()) {
            throw new GrayException("OOPS!!! The description and deadline cannot be empty.");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime deadlineDate;
        try {
            deadlineDate = LocalDateTime.parse(deadline, formatter);
        } catch (Exception e) {
            throw new GrayException("Cannot parse datetime yyyy-MM-dd HHmm");
        }
        return new AddDeadlineCommand(description, deadlineDate);
    }
}
