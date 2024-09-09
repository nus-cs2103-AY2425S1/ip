package friendlybot.command;

import java.util.List;

import friendlybot.Storage;
import friendlybot.Ui;
import friendlybot.task.Task;
import friendlybot.task.TaskList;

/**
 * FindCommand is a Command that finds tasks with descriptions that corresponds to the keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * A constructor for FindCommand.
     *
     * @param keyword The substring of the Task's description to check for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Prints the corresponding tasks that contains the keyword upon execution.
     *
     * @param tasks An instance of TaskList where the new task is added to.
     * @param ui An instance of Ui (User Interface) that handles the interactions between FriendlyBot and user.
     * @param storage An instance of Storage that loads tasks and saves tasks in a file.
     *
     * @return Returns a response String from FriendlyBot to the User.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder sb = new StringBuilder();
        List<Task> relatedTasks = tasks.findTasks(this.keyword);
        Ui.print("Here are the matching tasks in your list:");
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < relatedTasks.size(); i++) {
            int taskNumber = i + 1;
            Ui.print(taskNumber + "." + relatedTasks.get(i).toString());
            sb.append(taskNumber).append(".").append(relatedTasks.get(i).toString());
            if (i != relatedTasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
