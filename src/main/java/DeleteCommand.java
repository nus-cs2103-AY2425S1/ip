public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        // checks here
        Task deletedTask = taskList.getTask(index);
        taskList.removeTask(index);

        int taskListSize = taskList.getTaskListSize();

        String returnMessage = String.format("Got it. I've deleted this task:%n %s %nNow you have %d tasks in the list.", deletedTask, taskListSize);
        return new CommandResult(returnMessage);
    }
}