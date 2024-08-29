public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String USAGE = "unmark <taskNumber>";
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }


    @Override
    public CommandResult execute(TaskList taskList, TaskIO taskIO) {

        if (!taskList.isValidIndex(index - Ui.indexOffset)) {
            return new CommandResult("The index chosen is invalid.");
        }

        boolean alreadyMarked = taskList.getTask(index - Ui.indexOffset).getIsDone();

        if (!alreadyMarked) {
            return new CommandResult("The task is already unmarked.");
        }

        try {
            taskList.unmarkTask(index - Ui.indexOffset);
            taskIO.unmarkTask(taskList);
        } catch (DenimException e) {
            taskList.markTask(index - Ui.indexOffset);
            return new CommandResult(e.getMessage());
        }
        String returnMessage = String.format("Okay, I've marked this task as not done yet: \n %s\n", taskList.getTask(index));
        return new CommandResult(returnMessage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
