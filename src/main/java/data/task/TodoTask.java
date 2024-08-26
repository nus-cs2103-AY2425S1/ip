package data.task;

import java.util.ArrayList;

public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }

    @Override
    public ArrayList<String> toSaveArray() {
        ArrayList<String> rtr = super.toSaveArray();
        rtr.add("T");
        return rtr;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
