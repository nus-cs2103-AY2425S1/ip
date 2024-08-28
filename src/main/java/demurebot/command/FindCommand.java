package demurebot.command;

import demurebot.task.TaskList;
import demurebot.ui.Ui;

/**
 * Represents a command to find tasks with a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand.
     *
     * @param keyword Keyword to search for.
     */
    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    /**
     * Executes the command to find tasks with the keyword.
     *
     * @param tasks List of tasks.
     * @param ui Ui to interact with user.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getTask(i).getDescription().contains(keyword)) {
                System.out.println(tasks.getTask(i));
            }
        }
    }
}
