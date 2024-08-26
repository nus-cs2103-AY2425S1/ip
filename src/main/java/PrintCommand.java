public class PrintCommand extends Command {
    private TaskList list;

    public PrintCommand(TaskList list) {
        this.list = list;
    }

    public void execute() {
        list.printList();
    }

    public boolean isExit() {
        return false;
    }
}
