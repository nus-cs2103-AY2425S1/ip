public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String USAGE = "mark <taskNumber>";
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public CommandResult execute(TaskList taskList, TaskIO taskIO) {

        if (!taskList.isValidIndex(index - Ui.indexOffset)) {
            return new CommandResult("The index chosen is invalid.");
        }

        boolean alreadyMarked = taskList.getTask(index - Ui.indexOffset).getIsDone();

        if (alreadyMarked) {
            return new CommandResult("The task is already marked.");
        }

        try {
            taskList.markTask(index - Ui.indexOffset);
            taskIO.unmarkTask(taskList);
        } catch (DenimException e) {
            taskList.unmarkTask(index - Ui.indexOffset);
            return new CommandResult(e.getMessage());
        }
        String returnMessage = String.format("Okay, I've marked this task as done: \n %s\n", taskList.getTask(index));
        return new CommandResult(returnMessage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
