public class ToDos extends Task{
    // essentially a Task without time
    public ToDos(String description) {
        super(description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

}
