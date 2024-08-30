package Bob.Command;

import Bob.Exception.BobException;
import Bob.Tasks.Task;
import Bob.Storage.Storage;
import Bob.Ui.Ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FindCommand implements Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public void execute(ArrayList<Task> tasks, Storage storage, Ui ui) throws BobException {
        ArrayList<Task> matchingTasks = tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new)); // () -> new ArrayList<>()

        if (matchingTasks.isEmpty()) {
            ui.showError("I can't find any task with matching keyword... Try again");
        } else {
            ui.showMatchingTasks(matchingTasks);
        }
    }
}
