package hien.command;

import hien.exception.HienException;
import hien.main.Storage;
import hien.main.TaskList;
import hien.task.Task;
import hien.ui.UI;

public class DeleteCommand extends Command {
    private String input;
    private boolean isDeleteAll = false;
    public DeleteCommand(String input, boolean isExit, boolean isDeleteAll) {
        super(isExit);
        this.input = input;
        this.isDeleteAll = isDeleteAll;
    }
    private boolean isValidIndex(TaskList tasks, String index) throws HienException {
        if (index.isEmpty() || !index.matches("-?(0|[1-9]\\d*)")) {
            throw new HienException("☹ OOPS!!! The index of the task is either empty or not integer. Please try again!");
        }
        int i = Integer.parseInt(index);
        if (i < 1) {
            throw new HienException("☹ OOPS!!! Task index cannot be less than 1");
        } else if (i > tasks.size()) {
            throw new HienException("☹ OOPS!!! Task index cannot be greater than current number of tasks. "
                                    + "You currently only have " + tasks.size() + " tasks.");
        } else {
            return true;
        }
    }
    private String deleteTask(TaskList tasks, String input, Storage storage, UI ui) throws HienException {
        String index = input.substring(6).trim();
        String msg = "";
        if (isValidIndex(tasks, index)) {
            int i = Integer.parseInt(index);
            assert i >= 1 && i <= tasks.size();
            Task removedTask = tasks.getTask(i - 1);
            tasks.deleteTask(i - 1);
            storage.save(tasks);
            msg += ui.showMessage(" Got it. I've deleted this task:");
            msg += ui.showMessage("   " + removedTask);
            msg += ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");
        }
        return msg;
    }

    private String deleteAllTasks(TaskList tasks, UI ui, Storage storage) throws HienException {
        tasks.clear();
        storage.save(tasks);
        return ui.showMessage(" Got it. I've deleted all the tasks");

    }


    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws HienException {
        if (isDeleteAll) {
            return deleteAllTasks(tasks, ui, storage);
        } else {
            return deleteTask(tasks, input, storage, ui);
        }

    }
}
