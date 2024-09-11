package muffin;

public class History {
    private String command;
    private Task task;
    private int index;

    public History(String command, Task task, int index) {
        this.command = command;
        this.task = task;
        this.index = index;
    }

    public String getCommand() {
        return this.command;
    }

    public Task getTask() {
        return this.task;
    }

    public int getIndex() {
        return this.index;
    }
}
