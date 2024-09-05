public class UnmarkCommand extends Command {
    private final String input;
    public UnmarkCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        String[] string = this.input.split(" ", 2);
        int unmarkNum = Integer.parseInt(string[1]);
        storage.unmarkTask(unmarkNum, tasks);
    }
}
