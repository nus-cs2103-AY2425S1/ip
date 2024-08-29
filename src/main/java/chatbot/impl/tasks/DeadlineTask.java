package chatbot.impl.tasks;

public class DeadlineTask extends AbstractTask {

    private String deadline;

    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }


    @Override
    public String serialize() {
        return String.format("E | %d | %s | %s", isDone ? 1 : 0, description, deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }

}
