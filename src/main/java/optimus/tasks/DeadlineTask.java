package optimus.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import optimus.Storage;

/**
 * Task with deadline
 */
public class DeadlineTask extends Task {

    private final LocalDate deadline;

    /**
     * Constructor for when new DeadlineTask is initialised
     *
     * @param desc     - description
     * @param deadline - deadline
     */
    public DeadlineTask(String desc, LocalDate deadline) {
        super(desc);
        this.deadline = deadline;
    }

    /**
     * Constructor for when DeadlineTask is found in storage
     *
     * @param desc     - description
     * @param deadline - deadline
     * @param status   - task status
     */
    public DeadlineTask(String desc, LocalDate deadline, String status) {
        this(desc, deadline);
        if (Objects.equals(status, "1")) {
            super.markAsComplete();
        }
    }

    /**
     * Returns string representation of the task for UI purposes
     *
     * @return
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
     * @return
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
