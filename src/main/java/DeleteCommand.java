public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Ui ui, TaskList tasks) throws GrayException {
        if (index <= 0 || index > tasks.size()) {
            throw new GrayException("Not a valid index");
        }
        Task task = tasks.remove(index - 1);
        ui.say(String.format("""
                Noted. I've removed this task:
                    %s
                Now you have %d tasks in the list.""",
                task, tasks.size()));
    }
}
