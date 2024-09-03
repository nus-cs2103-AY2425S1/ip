public class Todo extends Task {
    public Todo(String description) {
        super(description);
        type = TaskType.TODO;
    }

    public static Todo fromTaskString(String taskString) {
        String[] parts = taskString.split("\\|");
        if (parts.length < 3)
            return null;

        Todo todo = new Todo(parts[2]);
        if (parts[1].equals("1"))
            todo.markAsDone();

        return todo;
    }


    protected String getTaskType() {
        return "T";
    }

    @Override
    public String toTaskString() {
        return String.format("%s|%d|%s",
                getTaskType(), isDone ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getTaskType(), super.toString());
    }
}
