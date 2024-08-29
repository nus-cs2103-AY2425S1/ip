package wenjigglybot;

// A task without a date and time
public class ToDoTask extends Task {
    public ToDoTask(String description) {
        super(description);
    }

    @Override
    public String taskType() {
        return "Todo wenjigglybot.Task";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}