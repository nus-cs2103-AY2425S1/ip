package Gumball;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate deadlineTime;

    /**
     *
     * @param desc The description of the deadline to be added including name and date.
     * @throws TaskException
     */
    public Deadline(String desc) throws TaskException {
        super("", desc);
        assert desc.startsWith("deadline");
        try {
            super.description = deadlineInputFormatter(desc);
            taskType = "[D]";
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException
                 | StringIndexOutOfBoundsException e) {
            throw new TaskException("Sorry, the description you " +
                    "gave does not follow the format for deadlines.\n" +
                    "\nIt should be ('description' /by 'date' in the format yyyy-mm-dd)" );
        }
    }

    private String deadlineInputFormatter(String desc) {
        String[] section = desc.substring(9).split("/by ");
        deadlineTime = LocalDate.parse(section[1]);
        String output = section[0] + "(by: " +
                deadlineTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        return output;
    }
}
