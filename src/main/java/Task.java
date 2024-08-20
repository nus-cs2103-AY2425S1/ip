public class Task {
    private String Taskname;
    public Task(String s) {
        Taskname = s;
    }

    @Override
    public String toString() {
        return Taskname;
    }
}
