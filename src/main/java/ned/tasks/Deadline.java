package ned.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import ned.Ui;
import ned.exceptions.InvalidTimeFormatException;
import ned.exceptions.MissingTaskDescriptionException;
import ned.exceptions.MissingTaskDueDateException;
import ned.exceptions.NedException;

/**
 * The {@code Deadline} class represents a task that needs to be completed by a specific date.
 * It extends the {@code Task} class by adding a deadline timing, which is stored as a {@code LocalDate}.
 *
 * <p>This class provides functionalities to:
 * <ul>
 *   <li>Create a new deadline task with a description and a due date.</li>
 *   <li>Parse and validate the deadline date provided in ISO 8601 format (yyyy-MM-dd).</li>
 *   <li>Display the task information along with its deadline in a user-friendly format.</li>
 *   <li>Convert the task to a text form suitable for file storage via {@link #toTextForm()}.</li>
 *   <li>Check for equality between {@code Deadline} tasks using {@link #equals(Object)}.</li>
 * </ul>
 *
 * <p><strong>Usage Example:</strong>
 * <pre>{@code
 * try {
 *     Deadline deadlineTask = Deadline.createDeadline("Submit assignment", "2023-10-15", false);
 *     System.out.println(deadlineTask);
 * } catch (NedException e) {
 *     System.out.println(e.getMessage());
 * }
 * }</pre>
 *
 * <p><strong>Note:</strong> The deadline date must follow the ISO 8601 format (yyyy-MM-dd).
 * If an invalid date is provided, a {@code NedException} is thrown with guidance on the correct format.
 *
 * @see Task
 * @see NedException
 */
public class Deadline extends Task {
    private LocalDate deadlineTiming;

    /**
     * Constructs a new {@code Deadline} task with the specified description, deadline timing, and completion status.
     * The deadline timing is parsed from a string in ISO 8601 format (yyyy-MM-dd).
     *
     * @param taskDescription The description of the deadline task.
     * @param deadlineTiming The deadline date as a string in ISO 8601 format (yyyy-MM-dd).
     * @param isDone {@code true} if the task is completed; {@code false} otherwise.
     * @throws NedException If the {@code deadlineTiming} is not in the correct ISO 8601 format.
     */
    protected Deadline(String taskDescription, String deadlineTiming, boolean isDone) throws NedException {
        super(taskDescription, isDone);
        try {
            this.deadlineTiming = LocalDate.parse(deadlineTiming);
        } catch (DateTimeParseException e) {
            throw new InvalidTimeFormatException("M'lord, the time formatting in /by does not follow ISO 8601 "
                    + "(yyyy-mm-dd). Here are examples of valid timings:\n" + Ui.INDENTATIONS + "2015-08-04\n"
                    + Ui.INDENTATIONS + "2015-08-04T10:11:30");
        }
        this.taskType = "D";
    }

    /**
     * Returns a formatted string representing the deadline timing in a user-friendly format.
     *
     * @return A string displaying the deadline date in the format "Month Day Year".
     */
    private String showTiming() {
        return String.format("%s %d %d", deadlineTiming.getMonth(), deadlineTiming.getDayOfMonth(),
                deadlineTiming.getYear());
    }
    @Override
    public String toString() {
        return super.toString() + " " + String.format("(by: %s)", showTiming());
    }

    /**
     * Creates a new {@code Deadline} task with the specified description, deadline timing, and completion status.
     * Validates that the task description and deadline timing are not blank before creating the task.
     *
     * @param taskDescription The description of the deadline task.
     * @param deadlineTiming The deadline date as a string in ISO 8601 format (yyyy-MM-dd).
     * @param taskStatus {@code true} if the task is completed; {@code false} otherwise.
     * @return A new {@code Deadline} task instance.
     * @throws NedException If the task description or deadline timing is blank.
     */
    public static Deadline createDeadline(String taskDescription, String deadlineTiming, boolean taskStatus)
            throws NedException {
        if (taskDescription.isBlank()) {
            throw new MissingTaskDescriptionException("M'lord, this saved deadline task has no task description!");
        } else if (deadlineTiming.isBlank()) {
            throw new MissingTaskDueDateException("M'lord, this saved deadline task has no due date!");
        }
        return new Deadline(taskDescription, deadlineTiming, taskStatus);
    }

    @Override
    public String toTextForm() {
        int status = this.isDone ? 1 : 0;
        return String.format("deadline|%d|%s|%s", status, this.taskDescription, this.deadlineTiming);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof Deadline)) {
            return false;
        }
        Deadline typeCastedObj = (Deadline) obj;
        return (isTaskDescriptionEqual(typeCastedObj)
                && isDeadlineTimingEqual(typeCastedObj));
    }

    private boolean isStatusEqual(Deadline typeCastedObj) {
        return this.isDone == typeCastedObj.isDone;
    }

    private boolean isDeadlineTimingEqual(Deadline typeCastedObj) {
        return this.deadlineTiming.equals(typeCastedObj.deadlineTiming);
    }

    private boolean isTaskDescriptionEqual(Deadline typeCastedObj) {
        return this.taskDescription.equalsIgnoreCase(typeCastedObj.taskDescription);
    }
}
