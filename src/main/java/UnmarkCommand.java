public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        taskList.unmarkTask(index);
        String returnMessage = String.format("Okay, I've marked this task as not done yet: \n %s\n", taskList.getTask(index));
        return new CommandResult(returnMessage);
    }
}
