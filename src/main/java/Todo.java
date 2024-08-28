public class Todo extends Command {
    String description;
    public Todo(String description) {
        this.description = description;
    }

    private static void taskAdded(TaskList list, Task t) {
        System.out.println("Got it. I've added this task:\n" + t);
        System.out.println("Now you have " + list.size() + (list.size() == 1 ? " task in the list." : " tasks in the list."));
    }
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        Task t = new TodoTask(description);
        list.add(t);
        taskAdded(list, t);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
