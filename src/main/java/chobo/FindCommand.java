package chobo;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> matchedTasks = new ArrayList<>();
        for (Task task : taskList.getTasks()) {
            if (task.getName().contains(keyword)) {
                matchedTasks.add(task);
            }
        }
        return ui.showMatchedTasks(matchedTasks);
    }
}
