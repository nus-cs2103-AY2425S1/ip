public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toFileString() {
        return "T , " + (isComplete() ? 1 : 0) + " , " + getName() + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
