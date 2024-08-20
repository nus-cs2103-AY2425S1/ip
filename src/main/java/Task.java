public class Task {
    private String name;
    private boolean finished;

    public Task(String name) {
        this.name = name;
        this.finished = false;
    }

    public boolean isDone() {
        return this.finished;
    }

    public void finish() {
        this.finished = true;
    }

    public void unfinish() {
        this.finished = false;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
