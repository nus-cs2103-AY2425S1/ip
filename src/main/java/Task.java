package main.java;

public abstract class Task {
    protected String name;
    protected boolean isDone = false;

    public Task(String name) {
        this.name = name;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unMark() {
        this.isDone = false;
    }

    @Override
    public String toString () {
        String res = "";
        if (this.isDone) {
            res += "[X] ";
        } else {
            res += "[ ] ";
        }
        res += this.name;
        return res;
    }
}
