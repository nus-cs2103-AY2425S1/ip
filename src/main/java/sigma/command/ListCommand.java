package sigma.command;
import sigma.Storage;
import sigma.TaskList;
import sigma.Ui;

import java.util.List;

public class ListCommand extends Commands {

    public ListCommand(String[] split) {
        super(split);
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.print("What the sigma? You have no tasks!");
        } else {
            StringBuilder s = ui.buildList(tasks);
            ui.print("You want a list? You got it!\n" + s.toString());
        }
    }

}
