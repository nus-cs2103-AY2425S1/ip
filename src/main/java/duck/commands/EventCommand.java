package duck.commands;

import duck.data.TaskList;
import duck.data.exception.DuckException;
import duck.data.task.Event;
import duck.storage.Storage;
import duck.ui.Ui;
import duck.util.Utils;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventCommand extends Command {
    public EventCommand(String message) {
        super(message);
    }
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DuckException {
        Event event = parseEvent(message);
        tasks.addTask(event, storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private Event parseEvent(String input) throws DuckException {
        // Regular expression to match the pattern for event
        Pattern pattern = Pattern.compile("(?i)^event\\s+(.+)\\s+/from\\s+(.+)\\s+/to\\s+(.+)$");
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            String description = matcher.group(1);
            String fromStr = matcher.group(2);
            String toStr = matcher.group(3);
            LocalDateTime from = Utils.convertToDateTime(fromStr);
            LocalDateTime to = Utils.convertToDateTime(toStr);
            if (!to.isAfter(from)) {
                throw new DuckException("The end time of an event should be after the start time!\n");
            } else {
                return new Event(description, from, to);
            }

        } else {
            throw new DuckException("""
                    Give me a valid event format!
                    event {description} /from {start} /to {end}
                    {start}: yyyy-mm-dd HHmm OR yyyy/mm/dd HHmm
                    {end}: yyyy-mm-dd HHmm OR yyyy/mm/dd HHmm
                    """);
        }
    }
}
