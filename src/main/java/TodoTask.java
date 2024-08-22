public class TodoTask extends Task{
    public TodoTask(String content) {
        super(content);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
