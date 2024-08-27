public class ToDo extends Task{

    ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String getState() {
        return String.format("T | %s", super.getState());
    }
}
