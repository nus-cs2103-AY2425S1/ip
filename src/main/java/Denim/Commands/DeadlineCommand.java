package Denim.Commands;

import Denim.Tasks.Deadline;
import Denim.Exceptions.DenimException;
import Denim.TaskList;
import Denim.Storage.TaskIO;
import Denim.Tasks.Task;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String USAGE = "deadline <task> /by <date>\nWhere date is in dd/MM/yyyy HHmm";
    private Task deadlineTask;

    public DeadlineCommand(String taskDescription, LocalDateTime deadline) {
        this.deadlineTask = new Deadline(taskDescription, deadline);
    }
    @Override
    public CommandResult execute(TaskList taskList, TaskIO taskIO) {
        try {
            taskIO.writeTaskData(deadlineTask);
        } catch (DenimException e) {
            return new CommandResult("Command Failed. Error:\n" + e.getMessage());
        }

        taskList.addTask(this.deadlineTask);
        int taskListSize = taskList.getTaskListSize();

        String returnMessage = String.format("Got it. I've added this task:%n %s %nNow you have %d tasks in the list.", deadlineTask, taskListSize);
        return new CommandResult(returnMessage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
