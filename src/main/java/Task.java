public class Task {
    private String task;
    public Task(String s){
        task = s;
    }
    public String toString() {
        return task;
    }
    public boolean isReal(){
        return task != "";
    }

}
