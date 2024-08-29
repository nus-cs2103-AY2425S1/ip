package cloudy;

public class Todo extends Task {

    public Todo(String description, boolean isMarked) {
        super(description, isMarked);
    }

    @Override
    public String printTaskOnList() {
        if (isMarked) {
            return "[T][X] " + this.description;
        } else {
            return "[T][ ] " + this.description;
        }

    }

    @Override
    public String toFileFormat() {
        return "T | " + (this.isMarked ? "1" : "0") + " | " + this.description;
    }
}
