package Gumball;

import java.time.LocalDate;

public class Events extends Task {
    private LocalDate eventStart;
    private LocalDate eventEnd;
    public Events(String desc) throws TaskException{
        super("",desc);
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
