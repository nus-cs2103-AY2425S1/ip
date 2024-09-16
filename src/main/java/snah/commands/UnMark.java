package snah.commands;

import snah.TaskList;
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
    public String execute(TaskList tasks, Storage storage) throws ParsingException {
        int taskIndex = Parser.getTaskIndex(getInput());
        tasks.get(taskIndex).unmarkAsDone();
        return "Nice! I've unmarked this task as undone:\n" + tasks.get(taskIndex);
    }

}
