package nebula.command;

import nebula.storage.Storage;
import nebula.task.Task;
import nebula.task.TaskList;
import nebula.ui.Parser;
import nebula.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class FindCommand extends Command {

    public FindCommand(String description) {
        super(description);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String command = getDescription();
        String keyword = Parser.splitCommandAndTaskDescription(command);

        // List to hold matching tasks
        ArrayList<Task> matchingTasks = new ArrayList<>();

        // Iterate through all tasks and check if the description contains the keyword
        for (int i = 0; i < tasks.getTaskListLength(); i++) {
            Task task = tasks.getTask(i);
            if ((task.getDescription()).contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        // Display the matching tasks
        if (matchingTasks.isEmpty()) {
            return ui.displayNoMatchingTasks();
        } else {
            return ui.displayMatchingTasks(matchingTasks);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
