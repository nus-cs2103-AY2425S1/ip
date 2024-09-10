package ai.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import ai.exception.AiException;

/**
 * A subtype of Task that has a due date.
 */
public class Deadline extends Task {
    private static final String TASK_TYPE = "D";
    private LocalDate deadline;

    /**
     * Creates a new Deadline object.
     * @param input String to be stored as description.
     * @param date in the format of d/M/yyyy HHmm or YYYY-MM-DD.
     * @throws AiException if the date format is incorrect.
     */
    public Deadline(String input, String date) throws AiException {
        super(input);

        try {
            if (date.contains("/")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                deadline = LocalDate.parse(date, formatter);
            } else {
                deadline = LocalDate.parse(date);
            }
        } catch (DateTimeParseException e) {
            throw new AiException("Awww shucksss, your date time format should either be d/M/yyyy HHmm "
                    + "or YYYY-MM-DD\n");
        }
    }

    /**
     * Creates a new Deadline object.
     * @param input String to be stored as description.
     * @param date in the format of YYYY-MM-DD.
     * @param isDone whether the task is done.
     * @throws AiException if the date format is incorrect.
     */
    public Deadline(String input, String date, Boolean isDone) {
        super(input, isDone);
        assert date.matches("\\d{4}-\\d{2}-\\d{2}") : "Date format must be YYYY-MM-DD";

        deadline = LocalDate.parse(date);
    }

    /**
     * Checks if the input date is equals to the date of the current object.
     *
     * @param date Date to be compared against.
     * @return boolean True if input date is equal to this.deadline.
     */
    public boolean isEqual(LocalDate date) {
        return deadline.isEqual(date);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (%s)", TASK_TYPE, super.toString(),
                deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String stringData() {
        return String.format("%s | %s | %s", TASK_TYPE, super.stringData(), deadline);
    }
}
