public class Task {
    private String desc;
    private Boolean isDone = false;
    private String done = " ";
    public Task(String desc){
        this.desc = desc;
    }

    public void markDone(){
        isDone = true;
        done = "X";
        System.out.println("Nice! I've marked this task as done:\n[" + done +"] " + desc);
    }

    public void markUndone(){
        isDone = false;
        done = " ";
        System.out.println("Ok! I've marked this task as not done yet:\n[" + done +"] " + desc);
    }

    public void print(){
        System.out.println("[" + done +"] " + desc);
    }

    public String stringValue(){
        return ("[" + done +"] " + desc);
    }
}
