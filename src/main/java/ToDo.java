public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.getDescription();
    }

    public String toFileFormat() {
        return "T | " + (this.getDone() ? "1" : "0") + " | " + this.getDescription();
    }

    public static ToDo parseTask(String taskData) {
        if (taskData.startsWith("T |")) {
            String[] parts = taskData.split(" \\| ");
            String description = parts[2].trim();
            ToDo todo = new ToDo(description);
            if (parts[1].trim().equals("1")) {
                todo.markDone();
            }
            return todo;
        }
        throw new IllegalArgumentException("Invalid ToDo format");
    }
}
