import java.util.List;
public class Delete {
    private int index;
    private List<Task> allTasks;
    private Task removedTask;
    public Delete(String description, List<Task> allTasks) throws CommandFoundButInvalidException{
        try {
            this.allTasks = allTasks;
            int num = Integer.parseInt(description);
            this.index = num - 1;
            // remove is the number if within range
            if (this.index >= 0 && this.index < allTasks.size()) {
                this.removedTask = allTasks.remove(this.index);
            } else {
                throw new InvalidSyntaxException("delete");
            }
        } catch (NumberFormatException e) {
            throw new InvalidSyntaxException("delete");
        }
    }

    public String message() {
        String s1 = "Noted. I've removed this task:";
        String s2 = String.format("Now you have %d tasks in the list", this.allTasks.size());
        return s1 + "\n" + " " + removedTask.toString() + "\n" + s2;
    }
}
