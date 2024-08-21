package task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
        this.type = "[T]";
        System.out.println("Successfully added task: " + this.toString());
        super.outputTaskCount();
    }

    @Override
    public String toString() {
            return this.type + "[" + this.status + "] - " + this.description;
    }
}
