package Task;

public class ToDo extends Task{
    public ToDo(String desc) {
        super(desc);
    }

    @Override
    public String getType() {
        return "[T]";
    }
}
