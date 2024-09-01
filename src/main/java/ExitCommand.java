class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showClosing();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
