package tasks;

public class ToDo extends Task {
    //private String input;

    public ToDo(String taskName) {
        super(taskName);
    }

    public static String extractName(String input) {
        String[] strings = input.split("\\s+", 2);
        return strings[1];
    }
    @Override
    public String displayTask() {
        String cross ="";
        if (super.getDone()) {
            cross = "[X]";
        } else {
            cross = "[ ]";
        }
        return "[T]" + cross + " " + super.getInput() + "\n";
    }
}
