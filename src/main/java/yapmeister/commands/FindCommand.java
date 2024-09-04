package yapmeister.commands;

import yapmeister.Storage;
import yapmeister.UI;
import yapmeister.task.InvalidMarkException;
import yapmeister.task.Task;
import yapmeister.task.TaskList;

import java.util.ArrayList;

public class FindCommand implements Command {
    String searchTerm;
    @Override
    public void execute(TaskList tasks, Storage storage, UI ui) throws Exception {
        ArrayList<Task> tasks2 = tasks.getFilteredArrayList(t -> t.getTaskName().contains(searchTerm));
        int i = 0;
        for (Task filteredTask : tasks2) {
            ui.displayString(String.format("%d. %s", i + 1, filteredTask.toString()));
            i++;
        }
        if (i == 0) {
            ui.displayString("No tasks to show");
        }
    }

    @Override
    public Command parse(String input) throws InvalidInputException {
        String[] inputs = input.split(" ");
        if (inputs.length < 2) {
            throw new InvalidInputException("Insufficient arguments for Find");
        }
        this.searchTerm = inputs[1];
        return this;
    }
}
