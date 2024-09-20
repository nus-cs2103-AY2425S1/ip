package diomon.command;

import diomon.Storage;
import diomon.task.TaskList;


public class FindCommand extends Command{
    public FindCommand(String input) {
        this.input = input;
    }
    @Override
    public void execute(TaskList tasks, Storage storage) {
        assert input != null;
        try {
            setResponse("Remember to keep your search history clean!\n" + tasks.fuzzyFind(input));
        } catch (RuntimeException e) {
            setResponse("Something went wrong with the search");
        }
    }
}
