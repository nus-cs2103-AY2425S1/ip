package Milo.TaskObj;

public class Todos extends Task {

    public Todos(String desc) {
        super(desc);
    }

    public Todos(String desc, Boolean isCompleted) {
        super(desc, isCompleted);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toTextString() {
        return "T" + super.toTextString();
    }
}
