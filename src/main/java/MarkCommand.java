public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public CommandResult execute(TaskList taskList) {
        taskList.markTask(this.index);
        String returnMessage = String.format("Okay, I've marked this task as done: \n %s\n", taskList.getTask(index));
        return new CommandResult(returnMessage);
    }
}
