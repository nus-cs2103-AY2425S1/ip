public class Todo extends Task{
    public Todo(String descr) {
        super(descr);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
