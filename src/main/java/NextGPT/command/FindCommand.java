package NextGPT.command;
import NextGPT.Storage;
import NextGPT.TaskList;
import NextGPT.Ui;
import NextGPT.task.Task;

/**
 * Subclass of Command that finds tasks in task list with given keyword.
 */
public class FindCommand extends Command{
    String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds tasks in saved tasklist that contains keyword.
     *
     * @param tasks Task list to search keyword in.
     * @param ui User interface that notifies user of completion.
     * @param storage Not required.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        TaskList resultsFound = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {

            String taskDescription = tasks.get(i).getDescription().toLowerCase();
            if (taskDescription.contains(keyword.toLowerCase())){
                Task foundTask = tasks.get(i);
                resultsFound.add(foundTask);
            }
        }
        ui.showList(resultsFound);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
