package meerkatpack;

public class Task {
    private boolean completed;
    private String name;

    public Task(String name) {
        this.completed = false;
        this.name = name;
    }

    public void markAsCompleted() {
        this.completed = true;
    }

    public void markAsIncomplete() {
        this.completed = false;
    }
    @Override
    public String toString() {
        String box;
        if (this.completed) {
            box = "[X]";
        } else {
            box = "[ ]";
        }
        return box + " " + this.name;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Returns a string that is more easily parseable by Parser when file is read upon start.
     * It takes the current task traits and stores them into a string. Similar to toString
     * method. Uses polymorphism, so this method body is a filler one, actual implementation
     * in the task subclasses.
     * @return The String to be saved into the write file
     */
    public String toParseableString() {
        return "hello";
    }
}
