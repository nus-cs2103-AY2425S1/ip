public class Todo extends Task {

    public Todo(String description) throws EmptyDescriptionException {
        super(description);
        if (description == null) {
            throw new EmptyDescriptionException("Description of todo cannot be empty.");
        }
    }

    public String getTypeIcon() {
        return "[T]";
    }

    @Override
    public String toString() {
        return this.getTypeIcon() + super.toString();
    }
}
