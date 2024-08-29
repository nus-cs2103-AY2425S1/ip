package TheBotFather.Command;

import TheBotFather.util.Storage;
import TheBotFather.util.TaskList;
import TheBotFather.util.TheBotFatherException;
import TheBotFather.util.Ui;

public class MarkCommand extends Command {

    private int index;
    private boolean isDone;

    public MarkCommand(String stringIndex, boolean isDone) throws TheBotFatherException {
        try {
            this.index = Integer.parseInt(stringIndex) - 1;
        } catch (NumberFormatException e) {
            throw new TheBotFatherException("How do you not know what a number is, jeez");
        }
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TheBotFatherException {
        if (isDone) {
            taskList.markAsDone(index);
            ui.printMarked(taskList.getTaskDescAtIndex(index));
        } else {
            taskList.unmark(index);
            ui.printUnmarked(taskList.getTaskDescAtIndex(index));
        }
        storage.toFile(taskList);

    }
}
