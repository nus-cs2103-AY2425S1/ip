public class MarkCommand extends Command {
    private final String input;

    public MarkCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        String[] string = this.input.split(" ", 2);
        int markNum = Integer.parseInt(string[1]);
        storage.markTask(markNum, tasks);
    }
}
