public class MarkCommand extends Command {
    public MarkCommand(Calebyyy calebyyy) {
        super(calebyyy);
    }

    @Override
    public void execute(String input) {
        int taskNumber = Integer.parseInt(input.substring(input.indexOf(' ') + 1)); 
        calebyyy.markTask(taskNumber);
    }
}
