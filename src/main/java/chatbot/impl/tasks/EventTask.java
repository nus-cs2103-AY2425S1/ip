package chatbot.impl.tasks;

public class EventTask extends AbstractTask {

    private String startTime;

    private String endTime;

    public EventTask(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), startTime, endTime);
    }

}
