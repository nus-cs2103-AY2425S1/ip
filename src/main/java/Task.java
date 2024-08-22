abstract class Task {
    private final String name;
    private boolean isDone = false;

    public Task(String name) {
        this.name = name;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        String str = "[";
        if (isDone)
            str += "X";
        else
            str += " ";
        str += "] " + name;
        return str;
    }
}
