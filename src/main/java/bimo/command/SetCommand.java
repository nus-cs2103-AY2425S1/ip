package bimo.command;

import bimo.tasks.Task;
import bimo.utils.Storage;
import bimo.utils.TaskList;
import bimo.utils.Ui;

/**
 * Creates a command to set priority of task.
 */
public class SetCommand extends Command {
    private Priority priority;
    private int index;

    /**
     * Instantiates the SetCommand.
     *
     * @param priority
     */
    public SetCommand(Priority priority, int index) {
        this.priority = priority;
        this.index = index;
    }

    /**
     * Sets priority of task.
     *
     * @param tasks List of user tasks.
     * @param ui User interface that interacts with users.
     * @param storage Storage that writes and load files.
     * @return Response of chatbot.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (!ui.findTaskInList(this.index, tasks)) {
            return ui.showTaskNotFoundError();
        }
        if (index < 0) {
            return "test";
        }
        assert index >= 0 && index < tasks.getLength()
                : "Index must not be out of bounds";
        Task taskPrioritised = tasks.getTask(this.index);
        String priorityLevel = String.valueOf(this.priority);
        taskPrioritised.setPriority(priorityLevel);
        storage.overwriteFile(tasks);
        String response = ui.printPrioritisedTaskMessage(taskPrioritised);
        return response;
    }
}
