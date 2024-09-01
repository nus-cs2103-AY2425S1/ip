package PHambot.command;

import PHambot.task.ToDo;
import PHambot.utils.Utilities;

public class ToDoCommand extends Command{
    public static final String COMMAND_WORD = "todo";

    private final ToDo toDo;

    public ToDoCommand(String toDo) {
        this.toDo = new ToDo(toDo);
    }

    public ToDo getToDo() {
        return this.toDo;
    }

    @Override
    public boolean executeCommand() {
        Utilities.OutlineMessage("Added: " + toDo.toString());
        return Command.taskList.addTask(this.toDo);
    }
}
