public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }
    @Override
    public String toString() {
        if (super.isComplete()) {
            return ("[T] " + super.toString());
        }
        return ("[T] " + super.toString());
    }

}
