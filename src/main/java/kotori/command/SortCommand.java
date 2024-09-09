package kotori.command;

import kotori.storage.Storage;
import kotori.taskList.TaskList;
import kotori.ui.Ui;

/**
 * A command to sort the task list
 * */
public class SortCommand extends Command {
    private TaskList list;
    private Storage storage;
    /**
     * Create a SortCommand
     * */
    public SortCommand(TaskList list, Storage storage) {
        this.list = list;
        this.storage = storage;
    }

    @Override
    public String execute() {
        list.sort();
        storage.updateFile(list);
        return Ui.printListWithMessages(list,
                "I have sorted the list for you~ ^_^\nThis is your new task list.");
    }
}
