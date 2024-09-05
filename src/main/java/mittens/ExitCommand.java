package mittens;

public class ExitCommand extends Command {
    public ExitCommand() {
        super();
        this.isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.save(tasks);

            ui.showGoodbyeMessage();
        } catch (MittensException e) {
            ui.showErrorMessage(e);
        }
    }
}
