public class Task {
    private String desc;
    private boolean isDone;
    
    Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    String getStatus() {
        return (isDone ? "X" : " ");
    }

    String getDesc() {
        return desc;
    }

    void changeStatus() {
        isDone = isDone ? false : true;
    }
}
