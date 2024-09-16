package snah.commands;

import snah.TaskList;
import snah.errors.InvalidTaskException;
import snah.errors.ParsingException;
import snah.util.Parser;
import snah.util.Storage;

/**
 * UnMark command to mark a task as undone
 */
public class UnMark extends Command {
    public UnMark(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws ParsingException, InvalidTaskException {
        int taskIndex = Parser.getTaskIndex(getInput());

        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new InvalidTaskException(taskIndex);
        }

        tasks.get(taskIndex).unmarkAsDone();
        tasks.save(storage);

        return "Nice! I've unmarked this task as undone:\n" + tasks.get(taskIndex);
    }

}
