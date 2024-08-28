package optimus.tasks;

import optimus.Storage;

import java.util.Objects;

public class ToDoTask extends Task {
    public ToDoTask(String desc) {
        super(desc);
    }

    public ToDoTask(String desc, String status) {
        this(desc);
        if (Objects.equals(status, "1")) {
            super.markAsComplete();
        }
    }

    @Override
    public String toString() {
        String s = "";
        s += "[T]";
        s += "[" + super.getStatusString() + "] ";
        s += super.getTaskDesc();
        return s;
    }

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
