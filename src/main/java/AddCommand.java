public class AddCommand extends Command {
    private Task task;

    AddCommand(Task task) {
        this.task = task;
    }

    void execute(TaskList taskList, Ui ui) {
        ui.reply(taskList.add(this.task));
    }
}
