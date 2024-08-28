import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    private String description;

    public EventCommand(String description) {
        this.description = description;
    }

    @Override
    public void actionable(TaskList list, Ui ui) {
        ui.showHorizontalLine();
        try {
            String[] cmdEvent = description.split("/from ", 2);
            String info = cmdEvent[0];
            String[] duration = cmdEvent[1].split(" /to ", 2);
            LocalDateTime from = LocalDateTime.parse(duration[0], formatter);
            LocalDateTime to = LocalDateTime.parse(duration[1], formatter);
            Task event = new Event(description, from, to);
            list.add(event);
            ui.showMessage("Fanny:\nGot it. I've added this task:");
            ui.showMessage(event.toString());
            ui.showMessage("Now you have " + list.getLength() + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showMessage("Event description and duration cannot be empty");
        } catch (DateTimeParseException e) {
            ui.showMessage("Please enter a valid date and time: YYYY-MM-DD HH:MM:SS");
        } finally {
            ui.showHorizontalLine();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
