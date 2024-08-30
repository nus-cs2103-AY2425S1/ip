package Gutti;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
    // does nothing
    }

    @Override
    public boolean isExit() {
        return true;
    }
}