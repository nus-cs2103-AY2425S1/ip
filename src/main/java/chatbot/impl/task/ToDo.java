package chatbot.impl.task;

public class ToDo extends Task{
    private static final String TYPE = "T";

    public ToDo(String description) {
        super(description);
    }

    /**
     * <p>
     *     Returns a string representation of the todo
     *     Used for printing or viewing
     * </p>
     * @return a string representation of the todo
     */
    @Override
    public String toString() {
        return "[" + TYPE + "]" + super.toString();
    }

    /**
     * <p>
     *     Returns a serialized representation of the todo
     *     Used for data transfer or storage
     * </p>
     * @return a string representation of the todo
     */
    @Override
    public String serialize() {
        return TYPE + "|" + super.serialize();
    }

    
}