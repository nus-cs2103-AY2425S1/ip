public class ToDo extends Task{
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toListString() {
        return "T" + super.toListString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
