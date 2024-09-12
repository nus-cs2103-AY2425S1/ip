package denim.commands;

import denim.TaskList;
import denim.exceptions.DenimException;
import denim.storage.WriteTaskFile;
import denim.tasks.Task;
import denim.tasks.Todo;

/**
 * Represents a todo command that can be executed.
 */
public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";
    public static final String COMMAND_USAGE = "todo <taskDescription>";
    private Task todoTask;

    public TodoCommand(String taskDescription) {
        todoTask = new Todo(taskDescription);
    }

    @Override
    public CommandResult execute(TaskList taskList, WriteTaskFile writeTaskFile) {
        try {
            writeTaskFile.writeTaskData(todoTask);
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
