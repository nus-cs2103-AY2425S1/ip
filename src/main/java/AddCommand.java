public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(this.task);
        ui.showMessage("Fine. I'll add '" + task.getTaskDescription() + "' to the list.\n\n\t"
                + this.task + "\n\nJust what you needed to boost your list to a grand total of "
                + taskList.size() + " task" + ((taskList.size() == 1)? "" : "s") + ". Lucky you.");
        storage.save(taskList);
    }
}
