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
        StringBuilder response = new StringBuilder();

        //TODO: Perhaps update findMatchingTasks to return a TaskList instead to we can reuse the toString method
        ArrayList<Task> matchingTasks = taskList.findMatchingTasks(this.keyword);

        if (matchingTasks.isEmpty()) {
            response.append("There are no tasks that contains the keyword!");
        } else {
            response.append("Here are the matching tasks in your list:");
            response.append(System.lineSeparator());
            for (int i = 0; i < matchingTasks.size(); i++) {
                response.append((i + 1)).append(".").append(matchingTasks.get(i)).append(System.lineSeparator());
            }
        }
        return response.toString();
    }
}
