package chatbot.impl.tasks;

public class TodoTask extends AbstractTask {

    public TodoTask(String description) {
        super(description);
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
