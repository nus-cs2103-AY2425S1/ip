package bob;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    public static Task from(String text) {
        String[] parameters = text.split("\\s\\|\\s");
        assert parameters.length == 3 : "Number of elements after splitting should be 3";
        return new ToDo(parameters[2], parameters[1].equals("1"));

    }

    @Override
    public String toText() {
        return String.format("T | %s | %s", super.isDone ? 1 : 0, super.description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

}
