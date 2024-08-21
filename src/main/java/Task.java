public class Task {
    protected String name;
    protected boolean isDone;
    protected String icon;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
        this.icon = " ";
    }

    public String getIcon() {
        return this.icon;
    }

    public void completeTask() {
        this.isDone = true;
        this.icon = "X";
    }

    public void undoTask() {
        this.isDone = false;
        this.icon = " ";
    }
    @Override
    public String toString() {
        return "[" + this.icon + "] " + name;
    }




}
