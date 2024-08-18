public class UnmarkCommand extends Command {
    public UnmarkCommand(Calebyyy calebyyy) {
        super(calebyyy);
    }

    @Override
    public void execute(String input) {
        int taskNumber = Integer.parseInt(input.substring(input.indexOf(' ') + 1));
        calebyyy.unmarkTask(taskNumber);
    }
}