package optimus.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import optimus.Storage;
import optimus.exceptions.InvalidDateFormatException;

/**
 * Task with deadline
 */
public class DeadlineTask extends Task {

    private LocalDate deadline;

    /**
     * Constructor for when new DeadlineTask is initialised
     *
     * @param desc     - description
     * @param deadline - deadline
     */
    public DeadlineTask(String desc, String deadline) throws InvalidDateFormatException {
        super(desc);

        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException("Dates for Deadline tasks must be in the YYYY-MM-DD format");
        }
    }

    /**
     * Constructor for when DeadlineTask is found in storage
     *
     * @param desc     - description
     * @param deadline - deadline
     * @param status   - task status
     */
    public DeadlineTask(String desc, String deadline, String status) throws InvalidDateFormatException {
        this(desc, deadline);
        if (Objects.equals(status, "1")) {
            super.markAsComplete();
        }
    }

    /**
     * Changes the deadline of the task
     * @param deadline - New deadline
     * @throws InvalidDateFormatException - if the deadline is not in YYYY-MM-DD format
     */
    @Override
    public void updateFirstDate(String deadline) throws InvalidDateFormatException {
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException("Dates for Deadline tasks must be in the YYYY-MM-DD format");
        }
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Returns string representation of the task for UI purposes
     *
     * @return - string representation of the task
     */
    @Override
    public String toString() {
        String s = "";
        s += "[D]";
        s += super.toString();
        s += String.format(" (by: %s)", this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        return s;
    }

    /**
     * Returns string representation of the task for storage purposes
     *
     * @return - string representation of the task
     */
    @Override
    public String getStorageString() {
        String s = "";
        s += "D";
        s += super.getStorageString();
        s += deadline;
        s += Storage.SPECIAL_CHAR;
        return s;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        DeadlineTask deadlineTask = (DeadlineTask) obj;

        return deadline.toString().equals(deadlineTask.deadline.toString())
                && Objects.equals(getTaskDesc(), deadlineTask.getTaskDesc());
    }
}
