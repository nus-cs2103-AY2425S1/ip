package mediell;

import mediell.task.Task;

public class Instruction {
    enum Type {
        INSERT,
        MARK,
        UNMARK,
        DELETE,
        SEARCH
    }
    private Type instructionType;
    private Task task;
    private int index;
    private String search;

    public Instruction(Type instructionType) {
        this.instructionType = instructionType;
        this.task = null;
        this.index = -1;
        this.search = "";
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Type getInstructionType() {
        return instructionType;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
