class ExitCommand extends Command {
    protected static String[] params = new String[] { "bye" };
    protected static int paramCount = 0;
    protected static String identifier = "bye";

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveFile(tasks);
    }
}
