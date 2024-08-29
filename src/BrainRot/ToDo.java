package BrainRot;

import BrainRot.Task;

public class ToDo extends Task {

    public ToDo(String task) {
        super(task);
    }
    @Override
    public String toFileString() {
        return "[T][" + (isDone ? "X" : " ") + "]/" + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }

}
