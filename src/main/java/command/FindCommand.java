package command;

import exception.KukiShinobuException;
import storage.Storage;
import task.Task;
import task.TaskList;
import task.Todo;
import ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String arguments) throws KukiShinobuException {
        // Checks for missing keyword and throws an error if so
        if (arguments.isEmpty()) {
            throw new KukiShinobuException("No keyword is provided!");
        }

        // argument is taskDescription
        this.keyword = arguments;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = taskList.findMatchingTasks(this.keyword);
//        ui.showLine();
        if (matchingTasks.isEmpty()) {
            ui.print("There are no tasks that contains the keyword!");
        } else {
            ui.print("Here are the matching tasks in your list:");
            for (Task task: matchingTasks) {
                ui.print(task.toString());
            }
        }
    }
}
