public class Todo extends Task {
    public Todo(String description) throws MurphyException{
        super(description);
    }

    public Todo(String description, boolean isDone) throws MurphyException{
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
