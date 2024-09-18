package talker.command;

import talker.Storage;
import talker.TalkerException;
import talker.Ui;
import talker.task.TaskList;

/**
 * Represents a find command to find certain keywords in task descriptions
 */
public class FindCommand extends Command {
    // string representing keyword
    private String input;

    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Executes find command
     *
     * @param list list of tasks to be searched through
     * @param ui ui object to print output
     * @param storage storage object to read/write file
     * @return String representing outcome of this event
     * @throws TalkerException if no tasks found
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws TalkerException {
        if (input.length() == 4) {
            throw new TalkerException("Missing keywords! Try again with: find <keyword(s)> ");
        }
        String keywords = input.substring(5).strip();
        return list.findTask(keywords, ui);
    }
}
