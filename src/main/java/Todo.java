public class Todo extends Task {
    public Todo(String description) {
        super(description);
        System.out.println(description);
    }

    public String getString() {
        return "[T]" + super.getString();
    }
}
