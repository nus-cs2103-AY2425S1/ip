public class ListCommand extends Command {
    void execute(TaskList taskList, Ui ui) {
        ui.reply(taskList.view());
    }
}
