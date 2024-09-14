public class DeleteCommand extends Command {

    public DeleteCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String command = getDescription();
        int taskNum = Parser.splitCommandAndTaskNumber(command);
        System.out.println(tasks.deleteTask(taskNum));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
