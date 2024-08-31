public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean executeCommand() {
        Command.taskList.unmarkTask(index - 1);
        Utilities.OutlineMessage("Unmarked task " + index + ":\n"
            + Command.taskList.getTask(index - 1).toString());
        return true;
    }
}
