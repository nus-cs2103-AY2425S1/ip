package morgana.commands;

import morgana.exceptions.MorganaException;
import morgana.parser.Parser;
import morgana.task.Event;
import morgana.task.Task;

public class EventCommand extends AddCommand {
    public EventCommand(String args) {
        super(args);
    }

    @Override
    Task createTask(String args) throws MorganaException {
        String[] parts = args.split(" /from | /to ");
        if (parts.length != 3) {
            throw new MorganaException("""
                    Invalid event format.
                    Usage: event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>
                    Example: event project meeting /from 2019-10-15 1400 /to 2019-10-15 1600""");
        }
        return new Event(parts[0], Parser.parseDateTime(parts[1]), Parser.parseDateTime(parts[2]));
    }
}
