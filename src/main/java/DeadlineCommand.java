import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private Task deadlineTask;

    public DeadlineCommand(String taskDescription, LocalDateTime deadline) {
        this.deadlineTask = new Deadline(taskDescription, deadline);
    }
    @Override
    public CommandResult execute(TaskList taskList) {
        taskList.addTask(this.deadlineTask);

        int taskListSize = taskList.getTaskListSize();

        String returnMessage = String.format("Got it. I've added this task:%n %s %nNow you have %d tasks in the list.", deadlineTask, taskListSize);
        return new CommandResult(returnMessage);
    }
}
