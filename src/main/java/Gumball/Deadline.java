package Gumball;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDate deadlineTime;

    /**
     *
     * @param desc The description of the deadline to be added including name and date.
     * @throws TaskException
     */
    public Deadline(String desc) throws TaskException {
        super("",desc);
        try {
            String[] section = desc.substring(9).split("/by ");
            LocalDate d1 = LocalDate.parse(section[1]);
            deadlineTime = d1;
            super.description = section[0] + "(by: " + d1.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
            taskType = "[D]";
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TaskException("Sorry, the desctiption you " +
                    "gave does not follow the format for deadlines.");
        }
    }
}
