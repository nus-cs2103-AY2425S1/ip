package snah.commands;

import snah.TaskList;
import snah.errors.ParsingException;
import snah.task.Event;
import snah.util.Parser;
import snah.util.Storage;

/**
 * Event command to add an event task
 */
public class CreateEvent extends Command {
    public CreateEvent(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws ParsingException {
        String[] eventPayload = Parser.getEventPayload(getInput());
        tasks.add(new Event(eventPayload[0], eventPayload[1], eventPayload[2]));
        tasks.save(storage);
        return String.format("Added event to list\n  %s", tasks.get(tasks.size() - 1));
    }

}
