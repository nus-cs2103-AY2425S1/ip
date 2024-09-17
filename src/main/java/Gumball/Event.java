package Gumball;

import java.time.LocalDate;

public class Event extends Task {
    private LocalDate eventStart;
    private LocalDate eventEnd;

    /**
     *
     * @param desc The description of the Event to be added including name the start and end times.
     * @throws TaskException
     */
    public Event(String desc) throws TaskException {
        super("", desc);
        assert desc.startsWith("event");
        try {
            String[] section = desc.substring(6).split("/from | /to ");
            super.description = section[0] + "(from: " + section[1] + " to: " + section[2] + ")";
            taskType = "[E]";
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TaskException("Sorry, the desctiption you " +
                    "gave does not follow the format for Gumball.Events.");
        }
    }
}
