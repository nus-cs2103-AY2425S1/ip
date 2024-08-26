public abstract class Task {
    private String desc;
    private Boolean isDone = false;
    private String done = " ";
    private String type = "";


    public Task(String desc) {
        this.desc = desc;
    }

    public Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDoneInit(isDone);
    }

    public void isDoneInit(boolean isDone){
        if (isDone) {
            isDone = isDone;
            done = "X";
        } else {
            isDone = isDone;
            done = " ";
        }
    }

    public void markDone() {
        isDone = true;
        done = "X";
        System.out.println("Nice! I've marked this task as done:\n[" + done + "] " + desc);
    }

    public void markUndone() {
        isDone = false;
        done = " ";
        System.out.println("Ok! I've marked this task as not done yet:\n[" + done + "] " + desc);
    }

    public String getDesc(){
        return desc;
    }

    public boolean isTaskDone(){
        return isDone;
    }

    public abstract String storeValue();

    public void print() {
        System.out.println("[" + done + "] " + desc);
    }

    public String stringValue() {
        return ("[" + done + "] " + desc);
    }
}
