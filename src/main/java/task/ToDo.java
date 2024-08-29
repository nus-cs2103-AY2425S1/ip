package task;

public class ToDo extends Task {
    public ToDo(String title) {
        super(title);
    }

    public static ToDo of(String[] args) {
        return new ToDo(args[1]);
    }

    @Override
    public String getTypeIcon() {
        return "T";
    }
}
