interface Command {
    public void execute(Storage storage, TaskList tasks, Ui ui) throws DownyException;
    public boolean isExit();

}
