package bing.command;

import bing.task.Event;
import java.time.LocalDateTime;

public class EventCommand extends AddCommand {
    public EventCommand(String info, LocalDateTime from, LocalDateTime to) {
        super(new Event(info, from, to));
    }
}
