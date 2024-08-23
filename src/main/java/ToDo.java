public class ToDo extends Task {

    public ToDo(String name, boolean status) {
        super(name, status);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
