package miku.command;

import miku.task.Task;
import miku.utility.Storage;
import miku.utility.TaskList;
import miku.utility.UI;

public class FindCommand extends Command{
    TaskList results = new TaskList();
    String string = "";

    public FindCommand(String string){
        this.string = string;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        taskList.searchList(string);
    }

    @Override
    public void setData() {

    }
}
