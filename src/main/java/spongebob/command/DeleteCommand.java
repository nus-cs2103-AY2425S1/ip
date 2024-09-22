package spongebob.command;

import spongebob.storage.Storage;
import spongebob.storage.TaskList;
import spongebob.task.Task;
import spongebob.ui.Ui;

/**
 * Command that performs a delete action to the tasklist and/or storage
 */
public class DeleteCommand extends Command {

    private String[] arguments;

    public DeleteCommand(String[] arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the command by finding the task to be deleted and removes it from storage and tasklist
     * @param taskList  Tasklist of Spongebob
     * @param ui    Ui containing all UI components
     * @param storage   Storage to keep all entries to a .txt file
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert !storage.isEmpty() : "storage is empty!";

        Task cur;
        try {

            // get index of task to be deleted
            int index = Integer.parseInt(arguments[1]) - 1;

            // delete task from cache and storage
            cur = taskList.getCache().get(index);
            taskList.delete(index);
            storage.delete(index);

            return ui.showTaskDeleted(cur, taskList.size());

        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return ui.showException(e);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String[] getArgs() {
        return arguments;
    }
}
