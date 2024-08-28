package rizzler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Task {
    protected final String name;
    protected boolean isDone;

    Task(String name) {
        this.name = name;
        this.isDone = false;
    }
    protected Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    void markAsDone() {
        this.isDone = true;
    }

    void unmark() {
        this.isDone = false;
    }

    boolean matches(String keyword) {
        Pattern pattern = Pattern.compile(keyword);
        Matcher matcher = pattern.matcher(this.name);
        boolean isMatched = false;
        while (matcher.find()) {
            isMatched = true;
        }
        return isMatched;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }

    String toSaveState() {
        return (this.isDone ? "1" : "0") + "/" + this.name + "\n";
    }
}
