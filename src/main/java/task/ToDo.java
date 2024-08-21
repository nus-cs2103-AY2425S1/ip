package task;

public class ToDo extends Task {
    public ToDo(String line) throws InvalidTaskException {
        super(line);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
