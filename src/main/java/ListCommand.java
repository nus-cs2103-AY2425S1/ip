public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String USAGE = "list";
    @Override
    public CommandResult execute(TaskList taskList, TaskIO taskIO) {
        String returnMessage = taskList.printList();
        return new CommandResult(returnMessage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
