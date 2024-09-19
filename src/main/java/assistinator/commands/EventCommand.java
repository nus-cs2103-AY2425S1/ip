package assistinator.commands;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import assistinator.AssistinatorException;
import assistinator.Storage;
import assistinator.TaskList;
import assistinator.tasks.EventTask;

/**
 * Represents event command.
 */
public class EventCommand extends Command {
    static final int EVENT_INPUT_LENGTH = 3;

    public EventCommand(String input) {
        super(input);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws AssistinatorException {
        EventTask newEvent = parseEvent(input);
        EventTask clashingEvent = tasks.hasTimeClash(newEvent);
        if (clashingEvent != null) {
            return String.format("Warning: This event clashes with %s.", clashingEvent.getDescription());
        }
        tasks.addTask(newEvent);
        return String.format(
                "I have added this task:\n%s\nYour evil agenda contains %d%s",
                newEvent,
                tasks.size(),
                tasks.size() == 1 ? " task" : " tasks"
        );
    }

    private EventTask parseEvent(String input) throws AssistinatorException {
        String[] parts = input.split(" /");
        if (parts.length != EVENT_INPUT_LENGTH || !parts[1].startsWith("from") || !parts[2].startsWith("to")) {
            throw new AssistinatorException("Please follow format: event {task description} /from {start} /to {end}");
        }
        String description = parts[0].substring(parts[0].indexOf(' ') + 1).trim();
        if (description.isEmpty()) {
            throw new AssistinatorException("Task description cannot be empty");
        }
        LocalDateTime start = LocalDateTime.parse(parts[1].substring(parts[1].indexOf(' ') + 1), formatter);
        LocalDateTime end = LocalDateTime.parse(parts[2].substring(parts[2].indexOf(' ') + 1), formatter);
        if (start.isAfter(end)) {
            throw new AssistinatorException("Start cannot be after end");
        }
        try {
            EventTask event = new EventTask(description, start, end);
            if (event.getStartTime().isAfter(event.getEndTime())) {
                throw new AssistinatorException("Start time must be before end time");
            }
            return event;
        } catch (DateTimeException e) {
            throw new AssistinatorException("Invalid date or time");
        }
    }
}
