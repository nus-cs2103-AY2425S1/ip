package snah.commands;

import snah.TaskList;
import snah.errors.ParsingException;
import snah.task.Deadline;
import snah.util.Parser;
import snah.util.Storage;

/**
 * Deadline command to add a deadline task
 */
public class CreateDeadline extends Command {
    public CreateDeadline(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws ParsingException {
        String[] deadlinePayload = Parser.getDeadlinePayload(getInput());
        tasks.add(new Deadline(deadlinePayload[0], deadlinePayload[1]));
        tasks.save(storage);
        return String.format("Added deadline to list\n  %s", tasks.get(tasks.size() - 1));
    }

}
