package monique.command;

import monique.*;
import monique.exception.MarkException;
import monique.storage.Storage;
import monique.tasklist.TaskList;
import monique.ui.Ui;

public class GuideCommand extends Command {
    public GuideCommand(){
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MarkException {
        System.out.println(GuideText.GUIDE);
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
