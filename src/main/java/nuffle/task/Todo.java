package nuffle.task;

public class Todo extends Task {
    public Todo(String description) {
        // Constructor for Todo class
        super(description);

    }

    @Override
    public String toString() {
        // Add a [T] at the front of task description (parent class)
        return "[T]" + super.toString();
    }

    public String saveFormat() {
        String temp;
        if (isDone) {
            temp = "1";
        } else {
            temp = "0";
        }
        return "T | " + temp + " | " + description;
    }
}
