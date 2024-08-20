public class ToDo extends Task{
    public ToDo(String _description) {
        super(_description);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
