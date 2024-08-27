public abstract class Command {
    protected TaskList taskList;

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public abstract String execute();
}
