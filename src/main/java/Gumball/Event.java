package Gumball;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    /**
     *
     * @param desc The description of the Event to be added including name the start and end times.
     * @throws TaskException
     */
    public Event(String desc) throws TaskException {
        super("", desc);
        assert desc.startsWith("event");
        try {
            super.description = eventInputFormatter(desc);
            taskType = "[E]";
        } catch (ArrayIndexOutOfBoundsException e ) {
            throw new TaskException("Sorry, the description you " +
                    "gave does not follow the format for Events.\n" +
                    "\nIt should be ('description' /from time /to time)");
        }
    }

    private String eventInputFormatter(String desc) throws TaskException {
        if(desc.equals("event")) {
            throw new TaskException("Sorry but the Event description cannot be empty." +
                    "\\nIt should be ('description' /from time /to time)");
        }
        String[] section = desc.substring(6).split("/from | /to ");
        String output = section[0] + "(from: " + section[1] + " to: " + section[2] + ")";
        return output;
    }
}

