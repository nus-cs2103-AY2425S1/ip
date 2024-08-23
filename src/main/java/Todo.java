public class Todo extends Task {

    public Todo (String description) {
        super(description);
    }

    @Override
    public String toStringDetails() {
        return String.format("[T]%s", super.toStringDetails());
    }
}
