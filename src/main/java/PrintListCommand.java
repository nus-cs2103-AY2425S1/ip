public class PrintListCommand extends Command {

    TaskList tasks;
    public PrintListCommand(TaskList tasks) {
        this.tasks = tasks;
    }
    @Override
    public void execute() {
        Ui.printList(tasks);
    }

    @Override
    public boolean isTerminated() {
        return false;
    }
}
