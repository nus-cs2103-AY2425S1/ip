package dumpling.command;

import dumpling.DumplingException;
import dumpling.Storage;
import dumpling.task.TaskList;
import dumpling.ui.Ui;

/**
 * NoteCommand class for Note commands used to add Notes to tasks
 */
public class NoteCommand extends Command {

    private int itemIdx;
    private String note;

    /**
     * Constructor of NoteCommand
     *
     * @param itemIdx target item index
     * @param note updated note to be added to task
     */
    public NoteCommand(int itemIdx, String note) {
        this.itemIdx = itemIdx;
        this.note = note.trim();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.echo(this.executeAndReturnLog(taskList, storage));
    }

    @Override
    public String executeAndReturnLog(TaskList taskList, Storage storage) {
        try {
            String message = taskList.updateTaskNotes(this.itemIdx, this.note);
            storage.save(taskList);
            return message;
        } catch (IndexOutOfBoundsException e) {
            throw new DumplingException(
                    "    Grrr... You tried to add a note at an index out of range! "
                            + String.format("There are only %d items.", taskList.getNumItems()));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
