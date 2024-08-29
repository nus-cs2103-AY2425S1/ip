package src.commands;

import src.Storage;
import src.Task;
import src.TaskList;
import src.Ui;
import src.commands.Command;

import java.util.ArrayList;

public class ListCommand extends Command {

    public ListCommand(boolean isActive, String input) {
        super(isActive, input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        displayList(storage.load());
        ui.showLine();
    }

    public static String displayList(ArrayList<Task> list) {
        String result = "";
        int i = 0;
        while (i < list.size()){
            String newLine = (i + 1) + ". " + list.get(i) + "\n";
            result += newLine;
            i++;
        }
        return result;
    }
}
