import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The EventCommand class represents a command to create an Event.
 */
public class EventCommand extends Command {
    private final String command;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");

    /**
     * Create a EventCommand.
     * @param command
     */
    EventCommand(String command) {
        this.command = command;
    }

    /**
     * Creates an Event in tasklist.
     * @param tasklist
     * @param ui
     * @param storage
     * @throws ReminderebotException
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws ReminderebotException {
        String[] eventInfo = command.split("/from ");
        String[] eventTiming = eventInfo[1].split("/to ");
        try {
            LocalDateTime fromTime = LocalDateTime.parse(eventTiming[0].trim(), formatter);
            LocalDateTime toTime = LocalDateTime.parse(eventTiming[1].trim(), formatter);
            Event event = new Event(eventInfo[0], fromTime, toTime);
            tasklist.addTask(event);
            ui.addTask(event, tasklist.length());
        } catch (IllegalArgumentException e) {
            // if datetime not in correct format
            throw new ReminderebotException("/from <datetime> and /to <datetime> should be of format dd/MM/yy HHmm");
        }
    }

    /**
     * Event does not exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
