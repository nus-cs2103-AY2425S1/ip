package conversage.command;

import conversage.exception.ConverSageException;
import conversage.storage.Storage;
import conversage.task.Task;
import conversage.task.TaskList;
import conversage.ui.Ui;

import java.util.List;


public class FindCommand extends Command {
    // Find command will have the keyword using which we must match
    String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ConverSageException {
        assert tasks != null : "TaskList cannot/should not be null";
        
        List<Task> tasksList = tasks.getTasks();
        String toRet = "";
        ui.showLine();

        int count = 1;

        for (Task task : tasksList) {
            if (task.toString().contains(keyword)) {
                if (count == 1) {
                    // show start mssage for first task
                    ui.showMessage("Unveiled by the sage's insight, these tasks resonate with your search:");
                    toRet = toRet + "Unveiled by the sage's insight, these tasks resonate with your search:\n";
                }
                ui.showMessage(count + " " + task.toString());
                toRet = toRet + count + " " + task.toString() + "\n";
                count++;
            }
        }
        if (count == 1) {
            ui.showMessage("The sage's wisdom reveals no tasks that align with your query. Perhaps the path you seek is yet to be trodden.");
            toRet = toRet + "The sage's wisdom reveals no tasks that align with your query. Perhaps the path you seek is yet to be trodden.";
        }
        ui.showLine();

        return toRet;
    }
}
