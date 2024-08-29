abstract class Command {
    abstract CommandResult execute(TaskList taskList, TaskIO taskIO);

    abstract boolean isExit();

}
