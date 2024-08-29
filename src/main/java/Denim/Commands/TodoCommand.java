package Denim.Commands;

import Denim.Tasks.Todo;
import Denim.Exceptions.DenimException;
import Denim.TaskList;
import Denim.Storage.TaskIO;
import Denim.Tasks.Task;

public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";
    public static final String USAGE = "todo <taskDescription>";
    private Task todoTask;

    public TodoCommand(String taskDescription) {
        this.todoTask = new Todo(taskDescription);
    }

    @Override
    public CommandResult execute(TaskList taskList, TaskIO taskIO) {
        try {
            taskIO.writeTaskData(todoTask);
        } catch (DenimException e) {
            return new CommandResult("Denim.Commands.Command Failed. Error:\n" + e.getMessage());
        }

        taskList.addTask(this.todoTask);
        int taskListSize = taskList.getTaskListSize();

        String returnMessage = String.format("Got it. I've added this task:%n %s %nNow you have %d tasks in the list.", todoTask, taskListSize);
        return new CommandResult(returnMessage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
