public class ToDo extends Task {

    public ToDo(boolean done, String task) {
        super(done, task);
    }

    @Override
    public String toString() {
        return super.getDone() ? "[T][X] " + super.getTask() : "[T][ ] " + super.getTask();
    }

}
