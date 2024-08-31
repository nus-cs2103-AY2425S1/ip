public abstract class Command {
    String desc;
    public abstract void execute(TaskList taskList,  UI ui, Storage storage);
    public abstract void setData();
    public Command(){}
}
