class ExitCommand implements Command {
    ExitCommand() {}

    public void execute(TaskList tasks, Ui ui, FileStorage fileStorage) throws RizzlerException {}

    public boolean isExit() {
        return true;
    }
}
