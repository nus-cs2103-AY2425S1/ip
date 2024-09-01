public class DeleteCommand extends Command {
    private int numToDelete;
    DeleteCommand(int i) {
        this.numToDelete = i;
    }

    void execute(TaskList taskList, Ui ui, Storage storage) {
        if (numToDelete > taskList.size()) {
            System.out.println("index out of bounds");
            return;
        }
        System.out.println("deleted: " + taskList.getTaskToString(numToDelete));
        taskList.remove(numToDelete - 1);
    }
}
