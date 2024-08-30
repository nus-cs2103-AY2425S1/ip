package chatbot.impl.tasks;

public class TodoTask extends AbstractTask {

    public TodoTask(String description) {
        super(description);
    }

    public static TodoTask deserialize(String line) {
        String[] parts = line.split(" \\| ");

        if (parts.length != 3 || !parts[0].equals("T")) {
            throw new IllegalArgumentException("Invalid TodoTask format");
        }

        TodoTask todoTask = new TodoTask(parts[2]);
        todoTask.setDone(parts[1].equals("1"));

        return todoTask;
    }

    // Todo: Handle case where '|' is part of description
    @Override
    public String serialize() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
