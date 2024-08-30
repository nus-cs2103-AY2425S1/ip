public class ToDo extends Task {
    private final static String type = "[T]";

    public ToDo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return type + super.toString();
    }
}
