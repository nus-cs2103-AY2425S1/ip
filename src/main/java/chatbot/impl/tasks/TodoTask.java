package chatbot.impl.tasks;

public class TodoTask extends AbstractTask {

    public TodoTask(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
