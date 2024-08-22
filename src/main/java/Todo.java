public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[T]");
        str.append(super.toString());
        return str.toString();
    }
}
