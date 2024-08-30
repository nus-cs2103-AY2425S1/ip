package Tasks;

public class Todos extends Task {

    public Todos(String action) {
        super(action);
    }

    public Todos(String action, boolean complete) {
        super(action, complete);
    }

    @Override
    public String toString() {
        return "T | " + super.toString();
    }
}
