public class DeadlineTasks extends Tasks{
    public DeadlineTasks(String message) {
        super(message);
    }

    String m1 = super.name.split("/by ", 2)[0];
    String m2 = super.name.split("/by ", 2)[1];
    @Override
    public String toString() {
        if (done) {
            return String.format("[D][X] %s (by: %s)", m1, m2);
        } else {
            return String.format("[D][] %s (by: %s)", m1, m2);
        }
    }
}
