public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }

    public String getType() {
        return "T";
    }

    @Override
    public String changeFormat() {
        return "T | " + (this.getStatusIcon().equals("X") ? "1" : "0") + " | " + this.description;
    }
}