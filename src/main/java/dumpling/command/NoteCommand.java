package dumpling.command;

import dumpling.Storage;
import dumpling.task.TaskList;
import dumpling.ui.Ui;

public class NoteCommand extends Command {

    private int itemIdx;
    private String note;

    public NoteCommand(int itemIdx, String note) {
        this.itemIdx = itemIdx;
        this.note = note;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.echo(this.executeAndReturnLog(taskList, storage));
    }

    @Override
    public String executeAndReturnLog(TaskList taskList, Storage storage) {
        String message = taskList.updateTaskNotes(this.itemIdx, this.note);
        storage.save(taskList);
        return message;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
