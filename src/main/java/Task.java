public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        setName(name);
        setDone(false);
    }

    public Task(String name, boolean isDone) {
        setName(name);
        setDone(isDone);
    }

    private void setName(String name) {
        this.name = name;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        String res = "[";
        if (isDone) {
            res = res + "X";
        } else {
            res = res + " ";
        }
        res = res + "] " + name;
        return res;
    }
}
