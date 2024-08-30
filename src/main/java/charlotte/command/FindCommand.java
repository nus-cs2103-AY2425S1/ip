package charlotte.command;

import charlotte.exception.CharlotteException;
import charlotte.storage.Storage;
import charlotte.task.Task;
import charlotte.task.TaskList;
import charlotte.ui.Ui;

public class FindCommand extends Command{
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CharlotteException {
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks.getTasks()) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.addTask(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            ui.showMessage("No tasks found matching the keyword: " + keyword);
        } else {
            ui.printLine();
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.getSize(); i++) {
                System.out.println((i + 1) + ". " + matchingTasks.getTask(i));
            }
            ui.printLine();
        }
    }
}
