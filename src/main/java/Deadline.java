public class Deadline extends Task {
    private String dueDate;

    public Deadline(String task, String dueDate) {
        super("[D] ", task);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        String string = " (due: " + this.dueDate + ")";
        return typeOfTaskString() + statusString() + taskString() + string;
    }
}
