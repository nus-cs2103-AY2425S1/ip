package bob.command;

import bob.exception.BobException;
import bob.tasks.Task;
import bob.storage.Storage;
import bob.ui.Ui;

import java.util.ArrayList;
import java.util.stream.Collectors; // collectors

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public String execute(ArrayList<Task> tasks, Storage storage, Ui ui) throws BobException {
        ArrayList<Task> matchingTasks = tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new)); // () -> new ArrayList<>()

        if (matchingTasks.isEmpty()) {
            return ui.showError("Bob can't find any task with matching keyword... Try again");
        } else {
            return ui.showMatchingTasks(matchingTasks);
        }
    }
}
