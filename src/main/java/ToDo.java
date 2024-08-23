public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String addTaskToString() {
        return super.addTaskToString() + "\n" + " ".repeat(5) + "[T] [ ] " + description;
    }

    @Override
    public String taskToString() {
        if (isDone) {
            return "[T] [X] " + description;
        }
        return "[T] [ ] " + description;
    }

    @Override
    public String markDoneToString() {
        return super.markDoneToString() + "\n" + " ".repeat(5) + "[T] [X] " + description;
    }

    @Override
    public String unmarkDoneToString() {
        return super.unmarkDoneToString() + "\n" + " ".repeat(5) + "[T] [ ] " + description;
    }

    @Override
    public String deleteTask() {
        if (isDone) {
            return super.deleteTask() + "\n" + " ".repeat(5) + "[T] [X] " + description;
        }
        return super.deleteTask() + "\n" + " ".repeat(5) + "[T] [ ] " + description;
    }

}