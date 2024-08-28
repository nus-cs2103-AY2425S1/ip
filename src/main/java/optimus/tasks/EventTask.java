package optimus.tasks;

import optimus.Storage;

import java.util.Objects;

public class EventTask extends Task {
    String start;
    String end;

    public EventTask(String desc, String start, String end) {
        super(desc);
        this.start = start;
        this.end = end;
    }

    public EventTask(String desc, String start, String end, String status) {
        this(desc, start, end);
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
        s += "[E]";
        s += "[" + super.getStatusString() + "] ";
        s += super.getTaskDesc();
        s += String.format(" (from: %s to: %s)", this.start, this.end);
        return s;
    }

    /**
     * Returns string representation of the task for storage purposes
     * @return
     */
    @Override
    public String getStorageString() {
        String s = "";
        s += "E";
        s += Storage.SPECIAL_CHAR;
        s += super.getStatusInt();
        s += Storage.SPECIAL_CHAR;
        s += super.getTaskDesc();
        s += Storage.SPECIAL_CHAR;
        s += start;
        s += Storage.SPECIAL_CHAR;
        s += end;
        s += Storage.SPECIAL_CHAR;
        return s;
    }
}
