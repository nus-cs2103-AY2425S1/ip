package BobChatBot.Command;

import BobChatBot.Tasks.Task;
import BobChatBot.Tasks.TaskList;
import BobChatBot.UI;

public class AddTaskCommand extends Command {

    private final Task task;
    public AddTaskCommand(Task task) {
        super(true);
        this.task = task;
    }

    public void execute(TaskList taskList) {
        taskList.addTask(this.task);
        UI.printAddTask(this.task);
        UI.printCurrentTaskListStatus(taskList);
    }
}
