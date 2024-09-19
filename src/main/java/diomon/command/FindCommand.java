package diomon.command;

import diomon.Storage;
import diomon.task.TaskList;
import diomon.ui.Ui;

public class FindCommand extends Command{
    public FindCommand(String input) {
        this.input = input;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assert input != null;
        try {
            setResponse("Remember to keep ur search history clean!\n" + tasks.fuzzyFind(input));
        } catch (RuntimeException e) {
            setResponse("Something went wrong with the search");
        }
    };
}
