package mel.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import mel.exceptions.ParseException;
import mel.exceptions.TaskException;

/**
 * Deadline class that represents a task with a deadlines.
 */
public class Deadline extends Task {
    private LocalDateTime by = null;

    /**
     * Constructs new Deadline task.
     * @param input task input with a deadline, in the format:
     *              deadline *task* /by *date* *time*.
     * @throws TaskException if input is of invalid format.
     */
    public Deadline(String input) throws TaskException {
        try {
            String s = processInput(input);
            processDateTime(s);
            assert by != null : "date/time should not be empty";
            if (task.isEmpty()) {
                throw new TaskException("deadline <task> /by <date> <time>");
            }
        } catch (ArrayIndexOutOfBoundsException | ParseException e) {
            throw new TaskException("deadline <task> /by <date> <time>");
        }
    }

    private String processInput(String input) {
        String[] temp = input.split("/by ");
        task = temp[0].split(" ", 2)[1].trim();
        String dateTime = temp[1].trim();
        return dateTime;
    }

    private void processDateTime(String input) throws ParseException {
        by = super.parseDateTime(input);
    }

    @Override
    public String toString() {
        assert !task.isEmpty() : "task field should not be empty";
        String mark = super.toString();
        String deadline = by.format(DateTimeFormatter.ofPattern("d LLL uuuu h.mma"));
        return "[D]" + mark + task + " (by: " + deadline + ")";
    }
}
