package atreides.command;

import atreides.task.Task;
import atreides.task.TaskList;
import atreides.ui.AtreidesException;
import atreides.ui.Storage;
import atreides.ui.Ui;

public class AddCommand implements Command {
    private final String msg;
    private final String[] words;

    public AddCommand(String msg) {
        this.msg = msg;
        words = msg.split(" ");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AtreidesException {
        Task newTask = TaskList.getTask(msg, words);
        tasks.addTask(newTask);
        String plural = tasks.size() == 1 ? " task" : " tasks";
        String response = "atreides.task.Task added\n"
                + newTask.toString().indent(2)
                + tasks.size() + plural + " in list\n";
        ui.showMessage(response);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
