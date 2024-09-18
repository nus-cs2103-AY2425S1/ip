package hien.command;

import hien.exception.HienException;
import hien.main.Storage;
import hien.main.TaskList;
import hien.ui.UI;

public class MarkCommand extends Command {
    private String input;
    private boolean isDone;
    private UI ui;

    public MarkCommand(String input, boolean isDone, boolean isExit) {
        super(isExit);
        this.input = input;
        this.isDone = isDone;
    }

    private boolean isValidIndex(String index, TaskList tasks) throws HienException {
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

    private void markTask(TaskList tasks, String input, boolean isDone, Storage storage, UI ui) throws HienException {
        String index = isDone ? input.substring(4).trim() : input.substring(6).trim();
        boolean isValidIndex = isValidIndex(index, tasks);
        if (isValidIndex) {
            int i = Integer.parseInt(index);
            if (isDone) {
                ui.showMessage(" Nice! I've marked this task as done:");
            } else {
                ui.showMessage(" OK, I've marked this task as not done yet:");
            }
            tasks.markTask(i - 1, isDone);
            ui.showMessage("   " + tasks.getTask(i - 1));
            storage.save(tasks);
        }
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws HienException {
        this.markTask(tasks, input, isDone, storage, ui);
    }
}