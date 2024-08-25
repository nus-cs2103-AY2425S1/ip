class ListCommand implements Command {
    public void execute(TaskList tasks, Ui ui, FileStorage fileStorage) {
        tasks.list();
    }
    public boolean isExit() {
        return false;
    }
}
