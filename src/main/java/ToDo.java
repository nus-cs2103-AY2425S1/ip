public class ToDo extends Task{
    public ToDo(String task) {
        super(task);
    }
    public ToDo() {
        super();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
