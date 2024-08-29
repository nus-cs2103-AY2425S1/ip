package denim.commands;

import denim.tasks.Todo;
import denim.exceptions.DenimException;
import denim.TaskList;
import denim.storage.TaskIo;
import denim.tasks.Task;

public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";
    public static final String COMMAND_USAGE = "todo <taskDescription>";
    private Task todoTask;

    public TodoCommand(String taskDescription) {
        todoTask = new Todo(taskDescription);
    }

    @Override
    public CommandResult execute(TaskList taskList, TaskIo taskIo) {
        try {
            taskIo.writeTaskData(todoTask);
        } catch (DenimException e) {
            return new CommandResult("Command Failed. Error:\n" + e.getMessage());
        }

        taskList.addTask(todoTask);
        int taskListSize = taskList.getTaskListSize();

        String returnMessage = String.format("Got it. I've added this task:%n %s %nNow you have %d tasks in the list.",
                todoTask, taskListSize);
        return new CommandResult(returnMessage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
