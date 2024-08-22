public class ToDo extends Task {

    public ToDo(String description) throws BobException {
        super(description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
