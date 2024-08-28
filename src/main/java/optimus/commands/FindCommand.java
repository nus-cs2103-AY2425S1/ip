package optimus.commands;

import optimus.Storage;
import optimus.TaskList;
import optimus.Ui;
import optimus.exceptions.OptimusExceptions;
import optimus.tasks.Task;

import java.util.stream.Stream;

public class FindCommand extends Command {
    String filter;
    public FindCommand(String filter) {
        this.filter = filter;
    }

    /**
     * @param storage
     * @param tasks
     * @param ui
     * @throws OptimusExceptions
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws OptimusExceptions {
        Stream<Task> filteredTasks = tasks.filterByKeyword(filter);
        ui.printToInterface("Here are the matching tasks in your list:");
        filteredTasks.forEach(task -> ui.printToInterface(task.toString()));
    }
}
