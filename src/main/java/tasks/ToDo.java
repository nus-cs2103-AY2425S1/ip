package tasks;

public class ToDo extends Task {
    //private String input;

    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String displayTask() {
        String cross = super.displayDone();
        String exclamationMark = super.displayPriority();
        return exclamationMark + "[T]" + cross + " " + super.getInput() + "\n";
    }
}
