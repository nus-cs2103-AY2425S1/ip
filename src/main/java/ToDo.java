public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String addTaskToString() {
        return super.addTaskToString() + "\n" + "  [T] [ ] " + description;
    }

    @Override
    public String taskToString() {
        if (isDone) {
            return "[T] [X] " + description;
        }
        return "[T] [ ] " + description;
    }

}