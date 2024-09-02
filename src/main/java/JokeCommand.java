public class JokeCommand extends CommandBase {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printJoke();
    }
}
