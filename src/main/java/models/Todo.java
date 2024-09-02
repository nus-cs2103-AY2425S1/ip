package models;
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }


    @Override
    public String serialize() {
        return String.format("T|%s|%s", this.getIsDone() ? "1" : "0", this.getDescription() );
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.getStatusIcon(), this.getDescription());
    }
}