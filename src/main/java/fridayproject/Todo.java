package fridayproject;

public class Todo extends Tasks{
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTypeIcon() {
        return "[T]";
    }
}