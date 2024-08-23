public class Task {
    private String desc;
    private String type;
    private boolean done;

    public Task(String desc, String type){
        this.desc = desc;
        this.type = type;
        this.done = false;
    }

    public void markDone(){
        this.done = true;
    }

    public void markNotDone(){
        this.done = false;
    }

    public String getStatus(){
        return (done ?  "X" : " ");
    }
    @Override
    public String toString(){
        return String.format("[%s][%s] %s", this.type, this.getStatus(), this.desc);
    }
}
