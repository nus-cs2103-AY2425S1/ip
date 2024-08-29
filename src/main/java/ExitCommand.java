public class ExitCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        this.isExit = true;
        ui.printExit();
    }
}
