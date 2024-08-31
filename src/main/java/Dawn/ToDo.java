package Dawn;
public class ToDo extends Task {
    public ToDo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return String.format("  [T][%s] %s", super.getStatusIcon(), super.toString());
    }
}
