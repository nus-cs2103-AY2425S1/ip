public class FindDateCommand extends Command {

    private String date;

    public FindDateCommand(String date) {
        this.date = date;
    }
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws KieTwoForOneException {
        try {
            ui.showSameDate(tasks.getTaskList(), this.date);
        } catch (KieTwoForOneException e) {
            throw new KieTwoForOneException(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
