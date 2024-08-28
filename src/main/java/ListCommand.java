public class ListCommand extends Command {
    public ListCommand(TaskList tasks) {
        super(tasks);
    }
    @Override
    public void execute() {
        Ui.printList(getTasks().getTasks());
    }
}
