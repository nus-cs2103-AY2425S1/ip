package myapp.HelperBuddy;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T][" + (this.getDone() ? "X" : " ")  + "] " + this.getDescription();
    }

    @Override
    public String toFileFormat() {
        return "T | " + (this.getDone() ? "1" : "0") + " | " + this.getDescription();
    }

    public static ToDo parseTask(String taskData) {
        String[] parts = taskData.split(" \\| ");
        String description = parts[2].trim();
        ToDo todo = new ToDo(description);
        if (parts[1].trim().equals("1")) {
            todo.markDone();
        }
        return todo;
    }
}
