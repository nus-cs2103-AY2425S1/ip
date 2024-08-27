public class CommandDeadline extends Command {
    private String[] params;

    public CommandDeadline(String command, String[] params) {
        super(command);
        this.params = params;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws BlitzException {
        Task temp = new Deadline(params[0], "D", Task.stringToLocaldatetime(params[1]), false);

        list.addTask(temp);
        storage.writeOneToFile(temp);
        ui.printTaskAddedWithDivider("D", list.size(), temp);
    }
}
