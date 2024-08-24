import java.time.LocalDateTime;

public class CommandDeadline extends Command {
    private String[] inputs;

    public CommandDeadline(String[] inputs) {
        this.inputs = inputs;
    }

    @Override
    public void execute() {
        String[] parts = inputs[1].split(" /by ");
        if (parts.length != 2 || !DateTimeHelper.isValidDateFormat(parts[1])) {
            Ui.printMessage("Invalid Deadline format, it should contain /by and a valid date.");
            return;
        }
        LocalDateTime dateTime = DateTimeHelper.parseDate(parts[1]);
        TaskList.addTask(new Deadline(false, parts[0], dateTime), false);
    }
}