public class DeleteCommand extends Command {
    private final String input;
    public DeleteCommand(String input) {
        this.input = input;
    }
    @Override
    public void execute(TaskList tasks, Storage storage) {
        String[] string = input.split(" ", 2);
        int deleteNum = Integer.parseInt(string[1]);
        storage.deleteTask(deleteNum, tasks);
    }
}
