public class ExitCommand extends Command {
    public boolean isExit() {
        return true;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showCommand("Bye. Hope to see you again soon!");
    }
}
