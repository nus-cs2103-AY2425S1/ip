package command;

import task.TaskList;

/**
 * Command that prints out list of tasks containing a certain keyword when executed.
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
    public void execute(TaskList list) {
        him.Ui.say(list.find(keyword));
    }

}
