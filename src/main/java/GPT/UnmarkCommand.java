package GPT;
public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(String input) {
        this.index = Integer.parseInt(input.split(" ")[1]) - 1;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.unmarkTask(index);
            storage.saveTasks(taskList.getTasks());
            ui.showTaskAdded(taskList.getTasks().get(index), taskList.getTasks().size());
        } catch (GPTException e) {
            ui.showError(e.getMessage());
        }
    }
}
