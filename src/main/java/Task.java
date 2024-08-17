public class Task {
    private String taskContent;

    public Task(String taskContent) {
        this.taskContent = taskContent;
    }

    @Override
    public String toString() {
        return this.taskContent;
    }



}
