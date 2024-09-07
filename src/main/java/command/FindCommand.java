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
    public String execute(TaskList taskList, Ui ui, Storage storage) {
//        StringBuilder response = new StringBuilder();
//
//        ArrayList<Task> matchingTasks = taskList.findMatchingTasks(this.keyword);
//
//        if (matchingTasks.isEmpty()) {
//            response.append("There are no tasks that contains the keyword!");
//        } else {
//            response.append("Here are the matching tasks in your list:");
//            for (Task task: matchingTasks) {
//                response.append(task.toString());
//            }
//        }
//        return response.toString();
        return "FindCommand";
    }
}
