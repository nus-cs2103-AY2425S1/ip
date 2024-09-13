package diomon.command;

import diomon.Storage;
import diomon.TaskList;
import diomon.ui.Ui;

public class FindCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            setResponse("Remember to keep ur search history clean!\n" + tasks.fuzzyFind(input));
        } catch (RuntimeException e) {
            setResponse("Something went wrong with the search");
        }
    };
}
