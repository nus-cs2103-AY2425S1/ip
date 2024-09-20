package optimus.tasks;

import java.util.Objects;


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
     * @param desc task description
     * @param status task completeness status
     */
    public ToDoTask(String desc, String status) {
        this(desc);
        if (Objects.equals(status, "1")) {
            super.markAsComplete();
        }
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    /**
     * Returns string representation of the task for UI purposes
     *
     * @return string representation the task
     */
    @Override
    public String toString() {
        String s = "";
        s += "[T]";
        s += super.toString();
        return s;
    }

    /**
     * Returns string representation of the task for storage purposes
     *
     * @return string representation of the task
     */

    @Override
    public String getStorageString() {
        String s = "";
        s += "T";
        s += super.getStorageString();
        return s;
    }
}
