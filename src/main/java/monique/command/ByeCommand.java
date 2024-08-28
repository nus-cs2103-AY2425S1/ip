package monique.command;

import monique.storage.Storage;
import monique.tasklist.TaskList;
import monique.ui.Ui;

public class ByeCommand extends Command {

    public ByeCommand(){
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        storage.save(tasks.getTaskList());
        ui.showGoodbye();
    }

    public  boolean isActive(){
        return false;
    }
}
