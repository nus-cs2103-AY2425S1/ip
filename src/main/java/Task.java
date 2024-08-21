public class Task {
    private Boolean isDone;
    private String Desc;

    Task(String Desc) {
        this.isDone = false ;
        this.Desc = Desc;
    }

    public Boolean isDone() {
        return this.isDone;
    }
    public String getDesc() {
        return this.Desc;
    }

    public void completeTask() {
        this.isDone = true;
    }

    public void undoTask() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[x] " + this.Desc;
        } else {
            return "[] " + this.Desc;
        }
    }
}
