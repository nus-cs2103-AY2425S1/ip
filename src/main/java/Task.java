public abstract class Task {

    protected String name;
    private boolean done = false;
    protected String taskTypeSymbol;

    public boolean isDone() {
        return this.done;
    }
    public void markAsDone() {
        this.done = true;
    }

    public void unmarkAsDone() {
        this.done = false;
    }

    @Override
    public String toString() {
        String doneString = done ? "X" : " ";
        return String.format("[%1$s] [%2$s]  %3$s", this.taskTypeSymbol, doneString, this.name);
    }

}
