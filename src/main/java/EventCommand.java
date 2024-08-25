import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

public class EventCommand extends Command {

    public EventCommand(HashMap<String, String> args) {
        super(args);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws QwertyException {
        String description = getArgs().get("main");
        String strFrom = getArgs().get("from");
        String strTo = getArgs().get("to");
        if (description == null) {
            throw new QwertyException("Your task is missing a name.");
        }
        if (strFrom == null) {
            throw new QwertyException("You didn't tell me when your event starts.");
        }
        if (strTo == null) {
            throw new QwertyException("Your didn't tell me when your event ends.");
        }
        try {
            LocalDateTime from = LocalDateTime.parse(
                    strFrom, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            LocalDateTime to = LocalDateTime.parse(
                    strTo, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            Task event = new Event(description, from, to);
            tasks.addTask(event);
            ui.showMessage("\nAdded this task:\n" + event
                    + "\nNow you have " + tasks.getSize() + (tasks.getSize() == 1 ? " task " : " tasks ")
                    + "in the list.\nBetter get to it.");
        } catch (DateTimeParseException e) {
            ui.showError("I don't like the way you write dates.\nUse this format: dd/MM/yyyy HHmm");
        }
    }

}
