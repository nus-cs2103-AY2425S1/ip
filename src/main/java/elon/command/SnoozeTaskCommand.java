package elon.command;

import elon.Storage;
import elon.Ui;
import elon.task.TaskList;

import java.time.LocalDateTime;

public class SnoozeTaskCommand extends Command{
    private int index;
    private LocalDateTime newDate;

    public SnoozeTaskCommand(int index, LocalDateTime newDate) {
        this.index = index;
        this.newDate = newDate;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws Exception {
        if (index < 0 || index >= list.listSize()) {
            return ui.showInvalidIndex();
        }
        list.snooze(index, newDate);
        storage.saveFile(list);
        return ui.snoozeTask(list.getTask(index));
    }
}
