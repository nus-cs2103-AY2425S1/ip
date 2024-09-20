package yapmeister.commands;

import java.util.ArrayList;

import yapmeister.Storage;
import yapmeister.UI;
import yapmeister.task.Task;
import yapmeister.task.TaskList;

/**
 * Represents Find user command that searches for a keyword in the tasks
 */
public class FindCommand implements Command {
    private String searchTerm;
    private boolean isExactMatch;

    @Override
    public void execute(TaskList tasks, Storage storage, UI ui) throws Exception {
        ArrayList<Task> tasks2 = null;
        if (isExactMatch) {
            tasks2 = tasks.getFilteredArrayList(t -> t.getTaskName().equals(searchTerm));
        } else {
            tasks2 = tasks.getFilteredArrayList(t -> t.getTaskName().contains(searchTerm));
        }
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

        assert inputs.length >= 2;

        if (!inputs[1].equals("-e")) {
            this.searchTerm = inputs[1];
            for (int i = 2; i < inputs.length; i++) {
                this.searchTerm += " " + inputs[i];
            }
            this.isExactMatch = false;
            return this;
        }
        this.isExactMatch = true;
        if (inputs.length < 3) {
            throw new InvalidInputException("Insufficient arguments for exact Find");
        }
        this.searchTerm = inputs[2];
        for (int i = 3; i < inputs.length; i++) {
            this.searchTerm += " " + inputs[i];
        }
        return this;
    }
}
