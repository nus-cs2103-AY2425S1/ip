package tecna.command;

import tecna.collection.TaskList;
import tecna.storage.Storage;
import tecna.ui.Ui;

public class ListCommand extends Command {
    public ListCommand(String message) {
        super(message);
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        super.execute(taskList, storage, ui);
        super.execute(taskList, storage, ui);
        return ui.printItems(taskList);
    }


    public static void main(String[] args) {
        ListCommand listCommand = new ListCommand("list");
        TaskList taskList = new TaskList();
        Storage storage = new Storage("");
        Ui ui = new Ui();
        System.out.println(listCommand.execute(taskList, storage, ui));
    }
}
