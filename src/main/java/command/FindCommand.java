package command;

import main.Storage;
import main.TaskList;
import main.Ui;

public class FindCommand extends Command {
    private final String input;
    public FindCommand(String input) {
        this.input = input;
    }
    @Override
    public void execute(TaskList tasks, Storage storage) {
        String[] string = this.input.split(" ", 2);
        String keyword = string[1];
        Ui.showKeywordTasks(tasks, keyword);
    }
}
