interface Command {
    public void execute(TaskList tasks, Ui ui, FileStorage fileStorage) throws RizzlerException;
    public boolean isExit();
}
