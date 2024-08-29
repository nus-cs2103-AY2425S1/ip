public class DateCommand extends Command {

    private String input;

    public DateCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws TalkerException {
        list.printTasksOn(input, ui);
    }

}
