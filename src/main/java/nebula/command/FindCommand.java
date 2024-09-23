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

        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (int i = 0; i < tasks.getTaskListLength(); i++) {
            Task task = tasks.getTask(i);
            if ((task.getDescription()).contains(keyword)) {
                matchingTasks.add(task);
            }
        }

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
