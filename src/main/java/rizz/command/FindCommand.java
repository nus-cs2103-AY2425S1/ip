package rizz.command;
import rizz.source.TaskList;

public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a new FindCommand with the given keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command by searching for tasks that contain the keyword
     * and displaying the matching tasks.
     *
     * @param tasks The TaskList to search through.
     */
    @Override
    public String execute(TaskList tasks) {
        TaskList foundTasks = tasks.findByKeyword(keyword);
        if (foundTasks.getLength() == 0) {
            return "No matching tasks found";
        } else {
            return "Here are the matching tasks in your list:\n" + foundTasks.toString();
        }
    }
}
