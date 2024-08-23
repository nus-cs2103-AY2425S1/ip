public class ToDo extends Task {
    public ToDo(String desc) {
        super(desc);
    }

    @Override
    public String getDesc() {
        return String.format("  [T][%s] %s", super.getStatusIcon(), super.getDesc());
    }
}
