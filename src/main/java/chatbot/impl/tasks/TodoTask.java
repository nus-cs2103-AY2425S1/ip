package chatbot.impl.tasks;

public class TodoTask extends AbstractTask {

    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
