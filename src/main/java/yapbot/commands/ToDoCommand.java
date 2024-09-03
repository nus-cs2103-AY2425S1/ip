package yapbot.commands;

import yapbot.exceptions.YapBotException;
import yapbot.tasks.Task;
import yapbot.tasks.ToDo;
import yapbot.util.Storage;
import yapbot.util.TaskList;
import yapbot.util.Ui;

public class ToDoCommand extends Command {
    private String taskDetails;

    public ToDoCommand(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws YapBotException {
        Task task = new ToDo(taskDetails);
        tasks.addTask(task);

        String successMessage = "Adding Task...\nSuccess\nTask added to database:\n" + "  "
                + task + "\n" + "Total tasks: " + tasks.size();
        ui.printOutput(successMessage);

        return true;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
