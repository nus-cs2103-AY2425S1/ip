package wenjieBot.commands;

import wenjieBot.Storage;
import wenjieBot.tasks.Task;
import wenjieBot.TaskList;
import wenjieBot.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {

    public ListCommand(boolean isActive, String input) {
        super(isActive, input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        System.out.println(displayList(storage.load()));
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
