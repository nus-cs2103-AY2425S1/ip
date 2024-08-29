public class DeleteCommand extends Command {
    private String args;

    public DeleteCommand(String args) throws JEFFException {
        super();
        if (args.isEmpty() || !Command.isNumeric(args)) {
            throw new JEFFException("You must provide one number after the command!");
        }
        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JEFFException {
        int i = Integer.parseInt(args);
        if (i <= 0 || i > tasks.size()) {
            throw new JEFFException("The number is outside the range!");
        }

        ui.showMessage("Ok, I will delete this task:");
        ui.showMessage("" + tasks.getTask(i - 1));
        tasks.deleteTask(i - 1);
        storage.updateSave(tasks.getTasks());
    }
}