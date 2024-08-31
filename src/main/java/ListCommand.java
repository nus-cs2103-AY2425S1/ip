public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show(String.format("These are your tasks:\n%s", tasks));
    }
}
