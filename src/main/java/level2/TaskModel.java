package level2;

public class TaskModel {
    private final String title;

    public TaskModel(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    @Override
    public String toString() {
        return this.title;
    }
}