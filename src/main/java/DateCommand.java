public final class DateCommand extends ListCommand {
    public DateCommand() {
        super();
    }
    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.printTasks(taskList, false);
    }
}
