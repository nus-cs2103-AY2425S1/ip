import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EventCommand extends Command {

    private final Event newEvent;
    private EventCommand(String input) {
        String eventInfo = input.split("event")[1];

        //Separate the fields
        String eventDescription = eventInfo.split("/from")[0].trim();
        String eventTime = eventInfo.split("/from")[1];

        String eventStartDateTime = eventTime.split("/to")[0].trim();
        String eventStartDate = eventStartDateTime.split(" ")[0].trim();
        String eventStartTime = eventStartDateTime.split(" ")[1].trim();

        String eventEndDateTime = eventTime.split("/to")[1].trim();
        String eventEndDate = eventEndDateTime.split(" ")[0].trim();
        String eventEndTime = eventEndDateTime.split(" ")[1].trim();

        LocalDate formattedStartDate = LocalDate.parse(eventStartDate);
        LocalTime formattedStartTime = LocalTime.parse(eventStartTime);
        LocalDateTime formattedStart = LocalDateTime.of(formattedStartDate, formattedStartTime);

        LocalDate formattedEndDate = LocalDate.parse(eventEndDate);
        LocalTime formattedEndTime = LocalTime.parse(eventEndTime);
        LocalDateTime formattedEnd = LocalDateTime.of(formattedEndDate, formattedEndTime);

        this.newEvent = Event.of(eventDescription, formattedStart, formattedEnd);
    }


    /**
     * Factory method for creating a new event command
     * @param input an event command in the format:
     *              event <description> /from <YYYY-MM-DD> <HH:MM> /to <YYYY-MM-DD> <HH:MM>
     * @return a new event command with those specifications
     */
    public static EventCommand of(String input) throws LewisException{
        try {
            return new EventCommand(input);
        } catch (IndexOutOfBoundsException e) {
            throw new LewisException("Hey, I can't understand your command. " +
                    "Type your event in this format:\n" +
                    "event <description> /from <YYYY-MM-DD> <HH:MM> /to <YYYY-MM-DD> <HH:MM> ");
        }
    }

    public static String getHelpDescription() {
        return "Creates a new event and enters it into the tasklist\n" +
                "Usage: event <description> /from <YYYY-MM-DD> <HH:MM> /to <YYYY-MM-DD> <HH:MM>";
    }

    public void execute() {
        TaskList.add(newEvent);
        Ui.printTask(newEvent);
        Storage.save();
    }
}
