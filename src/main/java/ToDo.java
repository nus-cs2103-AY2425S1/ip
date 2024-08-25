public class ToDo extends Task {

    public ToDo(String desc) {
        super(desc);
    }

    public String stringifyTask() {
        return String.format("T | %d | %s", super.getStatus() ? 1 : 0, super.getDesc());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
