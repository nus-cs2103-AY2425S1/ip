package LittleMissHelpful.Command;

import LittleMissHelpful.Exception.InvalidTaskFormatException;
import LittleMissHelpful.Exception.InvalidCommandException;
import LittleMissHelpful.Task.Task;
import LittleMissHelpful.TaskList;
import LittleMissHelpful.Ui;
import LittleMissHelpful.Storage;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String searchInput;

    public FindCommand(String searchInput) {
        this.searchInput = searchInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {

        ArrayList<Task> searchResults = new ArrayList<Task>();

        try {
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                if (t.getDescription().contains(this.searchInput)) {
                    searchResults.add(t);
                }
            }
            ui.showSearchResults(searchResults);
            
        } catch (InvalidTaskFormatException e) {
            throw new InvalidCommandException("Task number out of range. Please provide a valid task number.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
