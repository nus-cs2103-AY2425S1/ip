package chatbot.impl.task;

public class ToDo extends Task{
    private static final String TYPE = "T";

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + TYPE + "]" + super.toString();
    }

    @Override
    public String serialize() {
        return TYPE + "|" + super.serialize();
    }

    
}