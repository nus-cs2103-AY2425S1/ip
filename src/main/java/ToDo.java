public class ToDo extends Task {
    //private String input;

    public ToDo(String input) {
        super(input);
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
