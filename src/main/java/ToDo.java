public class ToDo extends Task {
    private final String task;
    public ToDo(String str) throws TaskException {
        try {
            task = str.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TaskException("todo <task>");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + task;
    }
}
