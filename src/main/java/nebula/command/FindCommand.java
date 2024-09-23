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

    /**
     * Executes the command to display the list of tasks that match the keyword.
     *
     * @param tasks   The list of tasks.
     * @param ui      The UI component that handles displaying the task list.
     * @param storage The storage component (not used in this method).
     */
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

    /**
     * Indicates whether this command will exit the application.
     *
     * @return false since the list command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
