public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean status) {
        super(description,status);
    }

    public ToDo markAsDone() {
        return new ToDo(this.description, true);
    }

    public ToDo markAsUndone() {
        return new ToDo(this.description, false);
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }

}
