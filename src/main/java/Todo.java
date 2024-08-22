public class Todo extends Task{

    public Todo(String task) throws TheBotFatherException {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
