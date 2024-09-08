public class Deadline extends Task {
    private String dueDate;

    public Deadline(String name, String dueDate) {
        super(name);
        String[] dateParts = dueDate.split(" ");
        StringBuilder dateSB = new StringBuilder(dateParts[0]).append(" ");
        for (int i = 1; i < dateParts.length - 1; i++) {
            dateSB.append(dateParts[i]).append(" ");
        }
        dateSB.append(dateParts[dateParts.length - 1]);
        this.dueDate = dateSB.toString();
    }

    @Override
    public String toFileString() {
        return "D , " + (isComplete() ? 1 : 0) + " , " + getName() + " , " + dueDate + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + dueDate + ")";
    }
}
