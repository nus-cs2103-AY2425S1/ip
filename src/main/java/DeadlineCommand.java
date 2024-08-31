import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DeadlineCommand extends Command {

    List<String> descriptionArray;
    List<String> dateArray;

    public DeadlineCommand(List<String> descriptionArray, List<String> dateArray) {
        this.descriptionArray = descriptionArray;
        this.dateArray = dateArray;
    }

    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        String dateStr = String.join(" ", dateArray);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
        Deadline newDeadline = new Deadline(String.join(" ", descriptionArray), dateTime);
        tasklist.addTask(newDeadline);
        try {
            storage.save(tasklist.getTaskList());
            output.append("Got it. I've added this deadline:\n").append(newDeadline.toString()).append("\n").append("Now you have ")
                    .append(tasklist.size()).append(" tasks in your list.\n");
            ui.printString(output.toString());
        } catch (AsuraException e) {
            ui.showError(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
