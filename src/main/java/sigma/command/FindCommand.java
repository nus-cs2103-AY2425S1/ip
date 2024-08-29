package sigma.command;

import sigma.Storage;
import sigma.TaskList;
import sigma.Ui;

public class FindCommand extends Commands {

    public FindCommand(String[] split) {
        super(split);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String keyword = split[1];
        TaskList matchingTasks = new TaskList(tasks.findTasks(keyword));
        if (matchingTasks.isEmpty()) {
            ui.print(String.format("What the sigma, I couldn't find any tasks with \"%s\" in them.", keyword));
        } else {
            ui.print(String.format("You're looking for \"%s\"? Here you go sis!\n%s", keyword,
                    ui.buildList(matchingTasks).toString()));
        }
    }

}
