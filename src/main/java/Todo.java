public class Todo extends Task {

    public Todo(boolean status, String description) {
        super(status, description);
    }

    @Override
    public String toFileString() {
        return "";
    }


    @Override
    public String toString() {
        return "[T][" + (this.getStatus() ? "X" : " ") + "] " + this.getDescription();
    }

}
