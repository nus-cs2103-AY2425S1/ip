package sigma.command;

import sigma.Storage;
import sigma.TaskList;
import sigma.Ui;

public class ListCommand extends Commands {

    public ListCommand(String[] split) {
        super(split);
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder s = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            s.append(i + ". " + tasks.get(i - 1).toString() + "\n");
        }
        if (tasks.isEmpty()) {
            ui.print("What the sigma? You have no tasks!");
        } else {
            ui.print("You want a list? You got it!\n" + s.toString());
        }
    }

}
