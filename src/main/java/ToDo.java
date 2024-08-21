public class ToDo extends Task{
    public ToDo(String name, int number) {
        super(name, number);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
