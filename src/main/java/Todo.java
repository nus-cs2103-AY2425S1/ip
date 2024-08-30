public class Todo extends Task{
    public Todo(String d) {
        super(d);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String additionalDescDetailsToFileFormat() {
        return "";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
