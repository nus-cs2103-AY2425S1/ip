package noisy;

public class Todo extends Task {
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }


    @Override
    public String formatTask() {
        String status = isDone ? "1" : "0";
        return "T | " + this.description + " | " + status;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
