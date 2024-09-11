package luffy;

/**
 * Represents a command that searches and displays all tasks
 * with the matching keyword in its representation
 */
public class FindCommand extends Command {

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void executeCmd(LuffyUI ui, Storage taskStorage, TaskList taskList) {

        TaskList tempList = taskList.findTasks(this.keyword);
        ui.showMatchingMessage();
        ui.displayTasks(tempList);
        ui.showLine();
    }
}
