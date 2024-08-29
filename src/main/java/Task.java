abstract class Task {
    private final String name;
    private boolean isDone = false;

    protected final String deli = "~!!";

    public Task(String name) {
        this.name = name;
    }


    public String taskData() {
        String str = " " + deli;
        if (isDone) {
            str += " 1 " + deli + " " + name;
        } else {
            str += " 0 " + deli + " " + name;
        }
        return str;
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
