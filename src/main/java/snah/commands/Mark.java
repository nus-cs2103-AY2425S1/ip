package snah.commands;

import snah.TaskList;
import snah.errors.ParsingException;
import snah.util.Parser;
import snah.util.Storage;

/**
 * Mark command to mark a task as done
 */
public class Mark extends Command {
    public Mark(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws ParsingException {
        int taskIndex = Parser.getTaskIndex(getInput());

        tasks.get(taskIndex).markAsDone();
        tasks.save(storage);

        return String.format("Alright, I will mark the task as done\n  %s", tasks.get(taskIndex));
    }

}
