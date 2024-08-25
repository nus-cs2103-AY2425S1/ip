public class ToDo extends Task {
    public ToDo(String desc) {
        super(desc);
    }

    @Override
    public String getDesc() {
        return "| T | " + super.getDesc();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
