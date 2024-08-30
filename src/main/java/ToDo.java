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
    public String toStorage() {
        return TYPE + "|" + super.toStorage();
    }

    
}