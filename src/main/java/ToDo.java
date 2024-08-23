public class ToDo extends Task {
    private final String typeOfTask = "[T] ";

    public ToDo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        if (taskIsDone()) {
            return typeOfTask + statusWhenDone() + taskString();
        } else {
            return typeOfTask + statusWhenNotDone() + taskString();
        }
    }
}
