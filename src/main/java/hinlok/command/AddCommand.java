package hinlok.command;

import java.util.ArrayList;

import hinlok.exceptions.HinlokException;
import hinlok.file.TaskFile;
import hinlok.parser.Parser.CommandType;
import hinlok.tasks.Task;
import hinlok.tasks.TaskList;
import hinlok.ui.Ui;

public class AddCommand extends Command {
    private final String taskDetails;
    private final CommandType type;

    public AddCommand(String taskDetails, CommandType type){
        this.taskDetails = taskDetails;
        this.type = type;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, TaskFile storage) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : taskList) {
            if (task.getName().contains(taskDetails)) {
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

    @Override
    public boolean isExit() {
        return false;
    }
}
