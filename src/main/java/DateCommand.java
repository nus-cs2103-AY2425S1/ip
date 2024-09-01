public class DateCommand extends Command{
    private String date;

    public DateCommand(String date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage s) {
        list.checkDate(date);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
