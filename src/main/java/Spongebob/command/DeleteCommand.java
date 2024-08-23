package Spongebob.command;

import Spongebob.storage.Storage;
import Spongebob.storage.TaskList;
import Spongebob.Ui;
import Spongebob.task.Task;

public class DeleteCommand extends Command {


    private String[] arguments;

    public DeleteCommand(String[] arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task cur;
        try {
            cur = taskList.getCache().get(Integer.parseInt(arguments[1]) - 1);
            taskList.delete(Integer.parseInt(arguments[1]) - 1);
            storage.delete(cur);
            ui.showTaskDeleted(cur, taskList.size());

        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            ui.showException(e);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
