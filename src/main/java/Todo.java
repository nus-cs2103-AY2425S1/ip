public class Todo extends Task {

    public Todo() {
        super();
    }

    public Todo(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "] " + description;
    }

}
