package optimus.tasks;

import java.util.Objects;

import optimus.Storage;

/**
 * Task without time constraint
 */
public class ToDoTask extends Task {
    public ToDoTask(String desc) {
        super(desc);
    }

    /**
     * Constructor for when ToDoTask in found in storage
     *
     * @param desc
     * @param status
     */
    public ToDoTask(String desc, String status) {
        this(desc);
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
        s += "[T]";
        s += "[" + super.getStatusString() + "] ";
        s += super.getTaskDesc();
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
        s += "T";
        s += Storage.SPECIAL_CHAR;
        s += super.getStatusInt();
        s += Storage.SPECIAL_CHAR;
        s += super.getTaskDesc();
        s += Storage.SPECIAL_CHAR;
        return s;
    }
}
