package hinlok.command;

import java.util.ArrayList;

import hinlok.exceptions.HinlokException;
import hinlok.file.TaskFile;
import hinlok.parser.Parser.CommandType;
import hinlok.tasks.Task;
import hinlok.tasks.TaskList;
import hinlok.ui.Ui;

public class FindCommand extends Command {

    private final String taskDetails;
    private final CommandType type;


    public FindCommand(String taskDetails, CommandType type) {
        this.taskDetails = taskDetails;
        this.type = type;
    }


    @Override
    public void execute(TaskList taskList, Ui ui, TaskFile taskFile) throws HinlokException {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList.getAllTasks()){
            if (task.getName().contains(taskDetails)){
                matchingTasks.add(task);
                }
            }

        if (matchingTasks.isEmpty()) {
            ui.showMessage("No matching tasks found.");
        } else {
            ui.showMessage("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            ui.showMessage((i + 1) + "." + matchingTasks.get(i).toString());
            }
        }
    }
}
