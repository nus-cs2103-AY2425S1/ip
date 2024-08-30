package atlas.commands;

import atlas.exceptions.AtlasException;
import atlas.storage.Storage;
import atlas.tasks.Task;
import atlas.tasks.TaskList;
import atlas.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String pattern;
    public FindCommand(String pattern) {
        this.pattern = pattern;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AtlasException {
        String filteredTasksOutput = tasks.find(this.pattern);
        ui.print(String.format("Here are the matching tasks in your list:\n%s", filteredTasksOutput));
    }
}
