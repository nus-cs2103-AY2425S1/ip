package chatbot.impl.tasks;

public class DeadlineTask extends AbstractTask {

    private final String deadline;

    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public static DeadlineTask deserialize(String line) {
        String[] parts = line.split(" \\| ");

        if (parts.length != 4 || !parts[0].equals("D")) {
            throw new IllegalArgumentException("Invalid DeadlineTask format");
        }

        DeadlineTask deadlineTask = new DeadlineTask(parts[2], parts[3]);
        deadlineTask.setDone(parts[1].equals("1"));

        return deadlineTask;
    }

    @Override
    public String serialize() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }

}
