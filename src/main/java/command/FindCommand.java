package command;

import task.TaskList;

/**
 * Command that returns list of tasks containing a certain keyword when executed.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructor for<code>FindCommand</code>.
     *
     * @param keyword Keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList list) {
        return him.Ui.say(list.find(keyword));
    }

}
