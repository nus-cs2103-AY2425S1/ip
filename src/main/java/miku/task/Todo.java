package miku.task;

import miku.utility.Priority;

/**
 * Represents the Todo task which has only descriptions.
 */
public class Todo extends Task {
    public Todo(String desc) {
        super(desc, false, Priority.MEDIUM);
    }

    public Todo(String desc, boolean isdone, Priority priority) {
        super(desc, isdone, priority);
    }

    @Override
    public String stringValue() {
        return ("[T]" + super.stringValue());
    }

    @Override
    public String storeValue() {
        return this.stringValue().substring(1, 2)
                + " | " + this.isTaskDone() + " | " + super.getDesc() + " | " + this.getPriority().toString() + "\n";
    }

}
