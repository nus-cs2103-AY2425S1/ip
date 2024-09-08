package opus;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toSaveFormat() {
        return "T|" + (isDone ? "1" : "0") + "|" + description;
    }

    public static Task fromFileFormat(String fullLine) {
        String[] parts = fullLine.split("\\|");
        ToDo todo = new ToDo(parts[2]);
        if (parts[1].equals("1")) {
            todo.markAsDone();
        }
        return todo;
    }

    @Override
    public String toString() {
        return "[T]" + (isDone ? "[X] " : "[ ] ") + description;
    }
}
