package main.java;

import static java.lang.Integer.parseInt;

public abstract class Task {
    protected String name;
    protected boolean isDone = false;


    public abstract String toSave();

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
