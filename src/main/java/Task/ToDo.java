package Task;

public class ToDo extends Task{
    public ToDo(String desc) {
        super(desc);
    }

    @Override
    String getType() {
        return "[T]";
    }
}
