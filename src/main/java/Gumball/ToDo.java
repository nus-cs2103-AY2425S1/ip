package Gumball;

import com.sun.jdi.event.BreakpointEvent;

public class ToDo extends Task {
    public ToDo(String desc) throws TaskException {
        super(desc.substring(4).trim(), desc);
        assert desc.startsWith("todo");
        if (desc.trim().length() < 5) {
            throw new TaskException("Sorry! The description for a ToDo cannot be empty.");
        }
        taskType = "[T]";
    }
}
