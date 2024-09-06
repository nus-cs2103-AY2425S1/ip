public class MarkCommand extends Command {

    private int position;

    public MarkCommand(int position) {
        this.position = position;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws KieTwoForOneException {
        String markedTask = tasks.markTask(position);
        ui.showMarkTask(markedTask);
        try {
            storage.saveFile(tasks.getTaskList());
        } catch (KieTwoForOneException e) {
            throw new KieTwoForOneException(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
