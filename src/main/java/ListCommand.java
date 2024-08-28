public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    @Override
    public CommandResult execute(TaskList taskList) {
        String returnMessage = taskList.printList();
        return new CommandResult(returnMessage);
    }
}
