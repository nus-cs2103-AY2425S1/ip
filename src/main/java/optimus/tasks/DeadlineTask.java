package optimus.tasks;

import optimus.Storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DeadlineTask extends Task {

    LocalDate deadline;

    public DeadlineTask(String desc, LocalDate deadline) {
        super(desc);
        this.deadline = deadline;
    }

    public DeadlineTask(String desc, LocalDate deadline, String status) {
        this(desc, deadline);
        if (Objects.equals(status, "1")) {
            super.markAsComplete();
        }
    }

    /**
     * Returns string representation of the task for UI purposes
     * @return
     */
    @Override
    public String toString() {
        String s = "";
        s += "[D]";
        s += "[" + super.getStatusString() + "] ";
        s += super.getTaskDesc();
        s += String.format(" (by: %s)", this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        return s;
    }

    /**
     * Returns string representation of the task for storage purposes
     * @return
     */
    @Override
    public String getStorageString() {
        String s = "";
        s += "D";
        s += Storage.SPECIAL_CHAR;
        s += super.getStatusInt();
        s += Storage.SPECIAL_CHAR;
        s += super.getTaskDesc();
        s += Storage.SPECIAL_CHAR;
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
