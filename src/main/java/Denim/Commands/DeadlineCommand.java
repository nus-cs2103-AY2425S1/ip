package denim.commands;

import java.time.LocalDateTime;

import denim.TaskList;
import denim.exceptions.DenimException;
import denim.storage.TaskIo;
import denim.storage.WriteTaskFile;
import denim.tasks.Deadline;
import denim.tasks.Task;


/**
 * Represents a deadline command that can be executed.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String COMMAND_USAGE = "deadline <task> /by <date>\nWhere date is in dd/MM/yyyy HHmm";
    private final Task deadlineTask;

    public DeadlineCommand(String taskDescription, LocalDateTime deadline) {
        deadlineTask = new Deadline(taskDescription, deadline);
    }
    @Override
    public CommandResult execute(TaskList taskList, WriteTaskFile writeTaskFile) {
        try {
            writeTaskFile.writeTaskData(deadlineTask);
        } catch (DenimException e) {
            return new CommandResult("Command Failed. Error:\n" + e.getMessage());
        }

        taskList.addTask(deadlineTask);
        int taskListSize = taskList.getTaskListSize();

        String returnMessage = String.format("Got it. I've added this task:%n %s %nNow you have %d tasks in the list.",
                deadlineTask, taskListSize);
        return new CommandResult(returnMessage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
