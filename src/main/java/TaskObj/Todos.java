package TaskObj;

public class Todos extends Task {

    public Todos(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
