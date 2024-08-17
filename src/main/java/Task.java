public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    @Override
    public String toString() {
        String check = isDone?"[X]":"[ ]";
        return " " + check + " " +this.description;
    }

    public void unMark(){
        this.isDone = false;

    }

    public void mark(){
        this.isDone = true;
    }


}